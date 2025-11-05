class Medico {
    String matricula;
    String nombre;       // puede ser "Dr. Emilio Pérez" concatenando Nombre + Apellido
    String especialidad;

    public Medico(String matricula, String nombre, String especialidad) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
}
