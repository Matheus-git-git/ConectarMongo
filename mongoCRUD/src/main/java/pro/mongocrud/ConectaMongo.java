package pro.mongocrud;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class ConectaMongo {
    //Create - insere os dados no banco
    
//método insertValues - com entrada ede dados nos paramentros:
    public void insereValores(int id, String nome, String profissao, boolean trabalha){
        System.out.println("insereValores");
        //clases de conexão com o mongo
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("pessoa");
        MongoCollection<Document> docs = db.getCollection("pess");
        
        Entrada user = criaUsuario(id, nome, profissao, trabalha);
//cria um user obj da classe conectar ,chamando o método
//createUser() - logo abaixo
        Document doc = criaDocumento(user);
//cria um doc que referencia o conteudpo de user do createDocument()
//obs, o Banco só entende objetos do tipo Document,

        docs.insertOne(doc);//insere no mongo o conteudo 
        mostraValores();
        System.out.println("insereValores ok");
    }
    public Entrada criaUsuario(int id, String nome, String profissao, boolean trabalha){
        //esse métoodo deve ser uma entrda externa 
        //interface, scanner ou JOptionPane
        Entrada u = new Entrada();
        u.setId(id);
        u.setNome(nome);
        u.setProfissao(profissao);
        u.setTrabalha(trabalha);
        return u;
    }
    public Document criaDocumento(Entrada user){
        Document docBuilder = new Document();
        docBuilder.append("__id",user.getId());
        docBuilder.append("nome", user.getNome());
        docBuilder.append("profissao", user.getProfissao());
        docBuilder.append("trabalha", user.getTrabalha());
        return docBuilder;
    }
    public void mostraValores(){
        System.out.println("mostraValores");
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("pessoa");
        MongoCollection<Document> docs = db.getCollection("pess");
        for (Document doc : docs.find()){
            System.out.println("item: "+doc);
    }
        System.out.println("mostraValores ok");
    }
    public void updateValores(String nome, String profissa){
        System.out.println("updateValores");
        
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("pessoa");
        MongoCollection<Document> docs = db.getCollection("pess");
        
        docs.updateOne(Filters.eq("nome",nome),Updates.set("profissao", profissa));
        System.out.println("Documento teve sucesso no update...");
        for(Document doc : docs.find()){
            System.out.println("item update : " + doc);
        }
    }
    public void deletaValores(int id, String nome){
        System.out.println("DeleteValores");
        
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoDatabase db = mongo.getDatabase("pessoa");
        MongoCollection<Document> docs = db.getCollection("pess");
        
        docs.deleteOne(Filters.eq("nome",nome));
        System.out.println("Documento deletado com sucesso no delete...");
        for(Document doc : docs.find()){
            System.out.println("item delete : " + doc);
        }
    }
}
