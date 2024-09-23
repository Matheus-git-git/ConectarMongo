package pro.mongocrud;
public class Runner {
    public static void main(String[] args) {
        ConectaMongo con =  new ConectaMongo();
       // con.insereValores(6,"Leandro Bernardi","MS.Dentista",true);
        con.mostraValores();
       
    }
}

