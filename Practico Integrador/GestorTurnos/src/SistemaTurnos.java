import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SistemaTurnos {
    List<Paciente> pacientes;
    List<Medico> medicos;
    List<Turno> turnos;

    public SistemaTurnos(){
        pacientes=new ArrayList<>();
        medicos=new ArrayList<>();
        turnos=new ArrayList<>();
    }

    public void cargarPacientes(String archivo){
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            br.readLine(); // <-- ¡Añadido! Saltar la línea de encabezado de pacientes.csv
            String linea;
            while((linea=br.readLine())!=null){
                String[] partes=linea.split(",");
                // El archivo pacientes.csv tenía 5 campos (DNI, Nombre, Apellido, FechaNacimiento, Telefono)
                // Aquí usamos partes[1] (Nombre) y partes[2] (Apellido) para el nombre completo.
                if(partes.length>=3){
                    String dni=partes[0].trim();
                    // Usamos Nombre + Apellido para tener el nombre completo, ya que en el CSV vienen separados.
                    String nombreCompleto = partes[1].trim() + " " + partes[2].trim();
                    if(!existePaciente(dni))pacientes.add(new Paciente(dni, nombreCompleto));
                }
            }
        }catch(Exception e){
            System.out.println("Error al leer pacientes: "+e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean existePaciente(String dni){
        for(Paciente p:pacientes)if(p.dni.equals(dni))return true;
        return false;
    }

    public void cargarMedicos(String archivo){
        // Usamos try-with-resources, es la mejor práctica
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            br.readLine(); // Saltar encabezado
            String linea;
            while((linea = br.readLine()) != null){
                String[] partes = linea.split(",");
                // El CSV de médicos tiene Matricula, Nombre, Apellido, Especialidad, Telefono
                if(partes.length >= 5){
                    String matricula = partes[0].trim();
                    // Juntamos partes[1] (Nombre) y partes[2] (Apellido)
                    String nombreCompleto = partes[1].trim() + " " + partes[2].trim();
                    String especialidad = partes[3].trim();
                    if(!existeMedico(matricula)) medicos.add(new Medico(matricula, nombreCompleto, especialidad));
                }
            }
        } catch(Exception e){
            System.out.println("Error al leer medicos: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private boolean existeMedico(String matricula){
        for(Medico m:medicos)if(m.matricula.equals(matricula))return true;
        return false;
    }

    public void cargarTurnos(String archivo){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");

        // Obtenemos el inicio de HOY (medianoche) para la comparación.
        LocalDate fechaSimulacion = LocalDate.of(2025, 10, 22);
        LocalDateTime hoyMedianoche = fechaSimulacion.atStartOfDay();

        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            br.readLine(); // saltar encabezado
            String linea;

            while((linea = br.readLine()) != null){
                String[] partes = linea.split(",");
                if(partes.length >= 6){
                    try{ // Bloque try/catch para parseo de fecha
                        String id = partes[0].trim();
                        String matriculaMedico = partes[1].trim();
                        String dniPaciente = partes[2].trim();
                        String fecha = partes[3].trim();
                        String hora = partes[4].trim();
                        LocalDateTime fechaHora = LocalDateTime.parse(fecha + " " + hora, dtf);
                        int duracionMin = 30;
                        String motivo = partes[5].trim();

                        // CONDICIÓN DE VALIDACIÓN CORREGIDA
                        // Usamos !isBefore para incluir todos los turnos desde la medianoche del día simulado.
                        boolean esTurnoValido = !existeTurno(id) &&
                                existePaciente(dniPaciente) &&
                                existeMedico(matriculaMedico) &&
                                duracionMin > 0 &&
                                !fechaHora.isBefore(hoyMedianoche); // <-- CORRECCIÓN FINAL

                        if(esTurnoValido){
                            turnos.add(new Turno(id, dniPaciente, matriculaMedico, fechaHora, duracionMin, motivo));
                        } else {
                            // Bloque de debugging
                            String motivoRechazo = "";
                            if (existeTurno(id)) motivoRechazo = "ID duplicado";
                            else if (!existePaciente(dniPaciente)) motivoRechazo = "Paciente no existe";
                            else if (!existeMedico(matriculaMedico)) motivoRechazo = "Médico no existe";
                            else if (fechaHora.isBefore(hoyMedianoche)) motivoRechazo = "Fecha pasada";

                            System.out.println("Turno rechazado: " + id + " - Motivo: " + motivoRechazo);
                        }
                    } catch (Exception parseException) {
                        System.out.println("Error de parseo en línea de turno: " + linea);
                    }
                }
            }
        } catch(Exception e){
            System.out.println("Error al leer turnos: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private boolean existeTurno(String id){
        for(Turno t:turnos)if(t.id.equals(id))return true;
        return false;
    }

    public void imprimirResumen(){
        System.out.println("Pacientes cargados: "+pacientes.size());
        System.out.println("Medicos cargados: "+medicos.size());
        System.out.println("Turnos cargados: "+turnos.size());
    }
}
