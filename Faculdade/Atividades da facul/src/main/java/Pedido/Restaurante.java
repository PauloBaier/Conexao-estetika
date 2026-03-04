package Pedido;

public class Restaurante {
    private String nomeFantsia;
    private String razaoSocial;
    private  String cnpj;
    private  String endereco;

    public Restaurante(String nomeFantsia, String razaoSocial, String cnpj, String endereco) {
        this.nomeFantsia = nomeFantsia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public String getNomeFantsia() {
        return nomeFantsia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }


}
