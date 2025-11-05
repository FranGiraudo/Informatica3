import java.util.Comparator;
import java.util.List;

public class OrdenamientoTurnos {

    public static void insertionSort(Turno[] arr, Comparator<Turno> cmp){
        for(int i=1;i<arr.length;i++){
            Turno key=arr[i];
            int j=i-1;
            while(j>=0 && cmp.compare(arr[j],key)>0){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }

    public static void shellSort(Turno[] arr, Comparator<Turno> cmp){
        int n=arr.length;
        for(int gap=n/2;gap>0;gap/=2){
            for(int i=gap;i<n;i++){
                Turno temp=arr[i];
                int j=i;
                while(j>=gap && cmp.compare(arr[j-gap],temp)>0){
                    arr[j]=arr[j-gap];
                    j-=gap;
                }
                arr[j]=temp;
            }
        }
    }

    public static void quickSort(Turno[] arr, Comparator<Turno> cmp, int low, int high){
        if(low<high){
            int pi=partition(arr,cmp,low,high);
            quickSort(arr,cmp,low,pi-1);
            quickSort(arr,cmp,pi+1,high);
        }
    }

    private static int partition(Turno[] arr, Comparator<Turno> cmp,int low,int high){
        Turno pivot=arr[high];
        int i=low-1;
        for(int j=low;j<high;j++){
            if(cmp.compare(arr[j],pivot)<=0){
                i++;
                Turno temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
            }
        }
        Turno temp=arr[i+1];
        arr[i+1]=arr[high];
        arr[high]=temp;
        return i+1;
    }

    // Comparadores
    public static Comparator<Turno> porHora(){
        return new Comparator<Turno>(){
            public int compare(Turno t1, Turno t2){
                return t1.fechaHora.compareTo(t2.fechaHora);
            }
        };
    }

    public static Comparator<Turno> porDuracion(){
        return new Comparator<Turno>(){
            public int compare(Turno t1, Turno t2){
                return t1.duracionMin-t2.duracionMin;
            }
        };
    }

    public static Comparator<Turno> porApellidoPaciente(List<Paciente> pacientes){
        return new Comparator<Turno>(){
            public int compare(Turno t1, Turno t2){
                String apellido1="",apellido2="";
                for(Paciente p:pacientes){
                    if(p.dni.equals(t1.dniPaciente))apellido1=p.nombre.split(" ")[p.nombre.split(" ").length-1];
                    if(p.dni.equals(t2.dniPaciente))apellido2=p.nombre.split(" ")[p.nombre.split(" ").length-1];
                }
                return apellido1.compareTo(apellido2);
            }
        };
    }

}
