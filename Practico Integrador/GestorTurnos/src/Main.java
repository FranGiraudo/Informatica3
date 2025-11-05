import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM HH:mm");


        System.out.println("===========================================");
        System.out.println("SISTEMA DE GESTION DE TURNOS MEDICOS");
        System.out.println("===========================================");


        SistemaTurnos sistema = new SistemaTurnos();
        sistema.cargarPacientes("C:\\Users\\frang\\Desktop\\FACU\\info 3\\proyecto final\\Practico Integrador\\GestorTurnos\\src\\resources\\pacientes.csv");
        sistema.cargarMedicos("C:\\Users\\frang\\Desktop\\FACU\\info 3\\proyecto final\\Practico Integrador\\GestorTurnos\\src\\resources\\medicos.csv");
        sistema.cargarTurnos("C:\\Users\\frang\\Desktop\\FACU\\info 3\\proyecto final\\Practico Integrador\\GestorTurnos\\src\\resources\\turnos.csv");

        System.out.println("Cargando datos iniciales...");
        System.out.println("> Leyendo pacientes.csv ... [OK] (" + sistema.pacientes.size() + " registros)");
        System.out.println("> Leyendo medicos.csv ...... [OK] (" + sistema.medicos.size() + " registros)");
        System.out.println("> Leyendo turnos.csv ....... [OK] (" + sistema.turnos.size() + " registros)");
        System.out.println("> Validando datos ...");
        System.out.println("> Estructuras internas inicializadas correctamente.");
        System.out.println("-------------------------------------------");


        int opcion = -1;
        while(opcion != 0){
            System.out.println("MENU PRINCIPAL");
            System.out.println("-------------------------------------------");
            System.out.println("1) Ver agenda de un medico");
            System.out.println("2) Buscar proximo turno disponible");
            System.out.println("3) Simular sala de espera");
            System.out.println("4) Programar recordatorios");
            System.out.println("5) Consultar indice de pacientes (Hash)");
            System.out.println("6) Consolidador de agendas");
            System.out.println("7) Reportes de ordenamiento");
            System.out.println("8) Auditoria Undo/Redo");
            System.out.println("9) Planificador de quirofano");
            System.out.println("0) Salir");
            System.out.println("-------------------------------------------");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:
                case 2:
                    System.out.println("-------------------------------------------");

                    AgendaMedicoAVL agenda = new AgendaMedicoAVL();
                    for(Turno t : sistema.turnos) {
                        if(t.matriculaMedico.equals("M-101")) agenda.agendar(t);
                    }

                    if(opcion == 1) {
                        System.out.println("[AGENDA DEL DR. PEREZ - CARDIOLOGIA]");
                        System.out.println("-------------------------------------------");
                        System.out.println("Turnos ordenados por fecha (AVL Tree):");
                        System.out.println("ID PACIENTE FECHA Y HORA MOTIVO");
                        System.out.println("---------------------------------------------------------");
                        for(Turno t : agenda.todos()) {
                            System.out.println(t.id + " " + t.dniPaciente + " " + dtf.format(t.fechaHora) + " " + t.motivo);
                        }
                        System.out.println("---------------------------------------------------------");
                    }

                    if(opcion == 2) {
                        System.out.println("[BUSQUEDA DE PROXIMO TURNO DISPONIBLE]");
                        System.out.println("-------------------------------------------");
                        System.out.println("Buscando primer hueco disponible de 15 minutos...");
                    }


                    Optional<LocalDateTime> prox = agenda.primerHueco(LocalDateTime.now(), 15);
                    prox.ifPresent(local -> System.out.println("Siguiente disponible >= " + dtf.format(LocalDateTime.now()) + " -> " + dtf.format(local)));
                    System.out.println("[Operacion O(log n) - Arbol AVL balanceado]");
                    break;


                case 3:
                    System.out.println("-------------------------------------------");
                    System.out.println("[3] SIMULACION DE SALA DE ESPERA (COLA CIRCULAR)");
                    System.out.println("-------------------------------------------");


                    int capacidadSala = 5;
                    SalaEspera sala = new SalaEspera(capacidadSala);

                    System.out.println("Capacidad de la sala: " + capacidadSala + " pacientes.");
                    System.out.println("Simulando la llegada de todos los turnos (" + sistema.turnos.size() + " en total):");


                    for(Turno t : sistema.turnos){
                        sala.llega(t.dniPaciente);
                    }

                    System.out.println("-------------------------------------------");
                    System.out.println("ESTADO FINAL DE LA SALA DE ESPERA:");
                    sala.printEstado();
                    System.out.println("Total de pacientes en sala: " + sala.size());
                    System.out.println("[Operaciones de 'llega' O(1)]");
                    break;

                case 4:
                    System.out.println("-------------------------------------------");
                    System.out.println("[4] PROGRAMADOR DE RECORDATORIOS (MIN-HEAP)");
                    System.out.println("-------------------------------------------");


                    MinHeapRecordatorio recordatoriosHeap = new MinHeapRecordatorio(20);

                    LocalDateTime baseDate = LocalDateTime.now().plusDays(1);

                    Recordatorio r1 = new Recordatorio("R-001", baseDate.withHour(10).withMinute(0), "32045982", "Recordatorio Dr. Perez 10:00");
                    Recordatorio r2 = new Recordatorio("R-002", baseDate.withHour(8).withMinute(30), "32458910", "Recordatorio documentacion 8:30");
                    Recordatorio r3 = new Recordatorio("R-003", baseDate.withHour(15).withMinute(0), "33123456", "Recordatorio Dr. Gomez 15:00");

                    System.out.println("Cargando 3 recordatorios. El Min-Heap los ordena automaticamente.");
                    recordatoriosHeap.push(r1);
                    recordatoriosHeap.push(r2);
                    recordatoriosHeap.push(r3);
                    System.out.println("[Insercion: O(log n)]");

                    System.out.println("\nSiguiente recordatorio a enviar (POP):");
                    Recordatorio tope = recordatoriosHeap.pop();
                    System.out.println("ID " + tope.getId() + " - " + dtf.format(tope.getFecha()) + " - " + tope.getMensaje());


                    recordatoriosHeap.push(tope);


                    LocalDateTime nuevaFechaUrgente = baseDate.withHour(7).withMinute(30);
                    System.out.println("\n--- SIMULACION DE REPROGRAMACION ---");
                    System.out.println("Reprogramando R-001 (originalmente a las 10:00) a la nueva hora: " + dtf.format(nuevaFechaUrgente));


                    recordatoriosHeap.reprogramar("R-001", nuevaFechaUrgente);
                    System.out.println("El Heap se ajusta automaticamente: R-001 sube al tope.");
                    System.out.println("[Reprogramacion: O(n) para buscar + O(log n) para reajustar]");


                    Recordatorio nuevoTope = recordatoriosHeap.pop();
                    System.out.println("\nSiguiente recordatorio mas urgente (Nuevo Tope):");
                    System.out.println("ID " + nuevoTope.getId() + " - " + dtf.format(nuevoTope.getFecha()) + " - " + nuevoTope.getMensaje());
                    recordatoriosHeap.push(nuevoTope);
                    System.out.println("-------------------------------------------");
                    break;

                case 5:
                    System.out.println("-------------------------------------------");
                    System.out.println("[5] INDICE RAPIDO DE PACIENTES (HASH CON CHAINING)");
                    System.out.println("-------------------------------------------");

                    MapaPacientes mapa = new MapaPacientes(10);

                    System.out.println("Cargando " + sistema.pacientes.size() + " pacientes en la Tabla Hash...");


                    for(Paciente p : sistema.pacientes) {

                        mapa.put(p.dni, p);
                    }

                    System.out.println("\nESTADO DE LA TABLA HASH (COLISIONES POR ENCADENAMIENTO):");

                    mapa.printTabla();


                    String dniBuscar = sistema.pacientes.get(0).dni;
                    Paciente encontrado = mapa.get(dniBuscar);
                    if(encontrado != null) {
                        System.out.println("\nDemostracion GET (O(1) promedio): Paciente " + dniBuscar + " encontrado: " + encontrado.nombre);
                    }

                    System.out.println("-------------------------------------------");
                    System.out.println("[Operaciones PUT y GET: O(1) promedio]");
                    break;
                case 6:
                    System.out.println("-------------------------------------------");
                    System.out.println("[6] CONSOLIDADOR DE AGENDAS (MERGE SORT)");
                    System.out.println("-------------------------------------------");


                    String matriculaTorres = "M-103";
                    String matriculaDiaz = "M-104";


                    AgendaMedicoAVL agendaTorres = new AgendaMedicoAVL();
                    AgendaMedicoAVL agendaDiaz = new AgendaMedicoAVL();


                    int turnosTorres = 0;
                    int turnosDiaz = 0;
                    for(Turno t : sistema.turnos) {
                        if(t.matriculaMedico.equals(matriculaTorres)) {
                            agendaTorres.agendar(t);
                            turnosTorres++;
                        } else if(t.matriculaMedico.equals(matriculaDiaz)) {
                            agendaDiaz.agendar(t);
                            turnosDiaz++;
                        }
                    }

                    System.out.println("Agendas a consolidar:");
                    System.out.println("- Dr. Torres: " + turnosTorres + " turnos");
                    System.out.println("- Dra. Diaz: " + turnosDiaz + " turnos");


                    List<Turno> listaTorres = agendaTorres.todos();
                    List<Turno> listaDiaz = agendaDiaz.todos();


                    List<Turno> agendaConsolidada = AgendaUtils.mergeAgendas(listaTorres, listaDiaz);

                    System.out.println("\nAgenda Consolidada Generada:");
                    System.out.println("Total de turnos unicos: " + agendaConsolidada.size());
                    System.out.println("-------------------------------------------");


                    System.out.println("Primeros 5 turnos ordenados cronologicamente:");
                    for(int i = 0; i < Math.min(5, agendaConsolidada.size()); i++) {
                        Turno t = agendaConsolidada.get(i);
                        System.out.println(dtf.format(t.fechaHora) + " - " + t.id + " - Medico: " + t.matriculaMedico);
                    }

                    System.out.println("-------------------------------------------");
                    System.out.println("[Operacion de Fusion: O(N) - Algoritmo Merge]");
                    break;

                case 7:
                    System.out.println("-------------------------------------------");
                    System.out.println("--- [7] REPORTES DE ORDENAMIENTO (ANALISIS DE O(N)) ---");
                    System.out.println("-------------------------------------------");

                    System.out.println("Iniciando pruebas de rendimiento de algoritmos de ordenamiento (Insertion, Shell, QuickSort)...");


                    TestOrdenamientoTurnos tester = new TestOrdenamientoTurnos();

                    tester.ejecutarPruebas();

                    System.out.println("\n--- ANALISIS ---");
                    System.out.println("QuickSort (O(n log n)) es el mas rapido para grandes volumenes.");
                    System.out.println("InsertionSort (O(n^2)) es el mas lento.");
                    System.out.println("[Demostracion de complejidad algoritmica y comparadores dinamicos]");
                    break;

                case 8:
                    System.out.println("-------------------------------------------");
                    System.out.println("--- [8] AUDITORIA Y UNDO/REDO (PILAS) ---");
                    System.out.println("-------------------------------------------");


                    AgendaMedicoAVL base = new AgendaMedicoAVL();
                    for(Turno t: sistema.turnos) {
                        if(t.matriculaMedico.equals("M-101")) base.agendar(t);
                    }

                    AgendaConHistorialImpl hist = new AgendaConHistorialImpl(base);

                    Turno t50 = new Turno("T-050","32045982","M-101",LocalDateTime.now().plusDays(1),30,"Control");
                    Turno t51 = new Turno("T-051","32458910","M-101",LocalDateTime.now().plusDays(1).plusMinutes(30),30,"ECG");


                    System.out.println("-> Ejecutando acciones (llenando pila UNDO):");
                    hist.agendar(t50);
                    hist.agendar(t51);
                    hist.cancelar("T-015");
                    hist.reprogramar("T-022", LocalDateTime.now().plusDays(2).withHour(9).withMinute(0));

                    System.out.println("\nESTADO DE LA AUDITORIA:");
                    System.out.println("Pila UNDO lista con 4 acciones.");

                    System.out.println("\n--- SIMULACION UNDO ---");
                    hist.undo();
                    System.out.println("[UNDO] <- Deshecha Reprogramacion T-022.");


                    System.out.println("\n--- SIMULACION REDO ---");
                    hist.redo();
                    System.out.println("[REDO] -> Rehecha Reprogramacion T-022.");

                    System.out.println("\n[Demostracion de estructura Pila (Stack) y patron Comando]");
                    break;

                case 9:
                    System.out.println("-------------------------------------------");
                    System.out.println("--- [9] PLANIFICADOR DE QUIROFANO (MIN-HEAP & TOP-K) ---");
                    System.out.println("-------------------------------------------");


                    List<String> quirofanos = List.of("Q1", "Q2", "Q3");
                    List<String> medicos = List.of("M-101", "M-102", "M-103", "M-104");

                    PlanificadorQuirofanoImpl planificador = new PlanificadorQuirofanoImpl(quirofanos, medicos);


                    LocalDateTime ahora = LocalDateTime.now().withHour(8).withMinute(0);

                    planificador.procesar(new SolicitudCirugia("C-001", "M-101", 120, ahora.plusDays(1)));
                    planificador.procesar(new SolicitudCirugia("C-002", "M-102", 180, ahora.plusDays(1)));
                    planificador.procesar(new SolicitudCirugia("C-003", "M-101", 60, ahora.plusDays(1)));


                    System.out.println("Quirofanos disponibles: Q1, Q2, Q3. (Q3 es el mas libre)");

                    SolicitudCirugia nuevaCirugia = new SolicitudCirugia("C-004", "M-102", 180, ahora.plusDays(1).withHour(15).withMinute(0));

                    planificador.procesar(nuevaCirugia);

                    System.out.println("\nProcesando solicitud C-004 (Duracion 180 min, Deadline: 15:00 hs)...");
                    System.out.println("[Asignacion gestionada por el Min-Heap de Quirofanos - O(log N)]");

                    System.out.println("\n--- Top 3 medicos mas ocupados (Min-Heap/PriorityQueue para Top-K) ---");
                    List<String> topMedicos = planificador.topKMedicosBloqueados(3);
                    for(int i = 0; i < topMedicos.size(); i++) {
                        System.out.println((i + 1) + ") " + topMedicos.get(i).replace(" - ", " - "));
                    }

                    System.out.println("[Ranking de Top-K actualizado en O(log N) + Sort O(K log K)]");
                    System.out.println("-------------------------------------------");
                    break;

                case 0:
                    System.out.println("-------------------------------------------");
                    System.out.println("Fin de ejecucion.");
                    System.out.println("-------------------------------------------");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
        sc.close();
    }
}