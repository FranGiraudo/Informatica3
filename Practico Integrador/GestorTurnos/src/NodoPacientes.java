class NodoPacientes {
    String dni;
    Paciente paciente;
    NodoPacientes next;

    public NodoPacientes(String dni, Paciente paciente){
        this.dni=dni;
        this.paciente=paciente;
        next=null;
    }
}
