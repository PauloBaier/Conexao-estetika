public class Cachorro  extends  Animal{

    Cachorro(String especie){
        super(especie);
    }

    @Override
    public void emitirSom(){
        System.out.println("Au au");
    }
}
