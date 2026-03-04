package Atividade_24_02_2026;

    public class Main {
        public static void main(String[] args) {

            Pessoa p1 = new Pessoa();
            p1.setNome("Camila");
            p1.setStatus((Status.INATIVO.toString()));

            System.out.println(p1.toString());

            Pessoa p2 = new Pessoa();
            p2.setStatus(Status.ATIVO.toString());

            try {
                p2.salvar(p2);

            } catch (ArithmeticException ex) {
                System.out.println("Erro ao criar por falta de nome: " + ex);

            } catch (RuntimeException ex){
                System.out.println("Falha ao criar pessoa: " + ex.getMessage());
            }

            System.out.println(p2.toString());
        }
    }

