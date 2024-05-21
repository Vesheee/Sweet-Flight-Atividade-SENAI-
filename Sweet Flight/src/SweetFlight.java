import java.util.Scanner;

public class SweetFlight {
    static Scanner scanner = new Scanner(System.in);
    static int[] aviao = new int[4];
    static int[] assentos = new int[4];
    static int[] assentosDisponiveis = new int[4];
    static Reserva[] checkin = new Reserva[80];
    static int numReservas = 0;

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("=================================================");
            System.out.println("|             Gestão de Reservas               |");
            System.out.println("=================================================");
            System.out.println("| 1. Registrar número de cada avião           |");
            System.out.println("| 2. Registrar quantidade de assentos         |");
            System.out.println("|    disponíveis em cada avião                |");
            System.out.println("| 3. Reservar passagem aérea                  |");
            System.out.println("| 4. Consultar reservas por avião             |");
            System.out.println("| 5. Consultar reservas por passageiro        |");
            System.out.println("| 6. Encerrar                                 |");
            System.out.println("=================================================");
            opcao = scanner.nextInt();
            limparTela();

            switch (opcao) {
                case 1:
                    registrarAviao();
                    break;
                case 2:
                    registrarAssentos();
                    break;
                case 3:
                    fazerReserva();
                    break;
                case 4:
                    consultaAviao();
                    break;
                case 5:
                    consultaPassageiro();
                    break;
            }
        } while (opcao != 6);
        System.out.println("Obrigado, Volte sempre");
    }

    static void registrarAviao() {
        for (int i = 0; i < 4; i++) {
            System.out.println("Digite o numero do " + (i + 1));
            aviao[i] = scanner.nextInt();
        }
        limparTela();
    }

    static void registrarAssentos() {
        for (int i = 0; i < 4; i++) {
            System.out.println("Digite o numero dos assentos do avião numero " + aviao[i]);
            assentos[i] = scanner.nextInt();
            if (assentos[i] > 20) {
                limparTela();
                System.out.println("Numero de assentos maior que o Total permitido");
                System.out.println("Digite todos os assentos disponíveis novamente");
                registrarAssentos();
            }
        }
        limparTela();
    }

    static void fazerReserva() {
        System.out.println("Digite o numero do avião");
        int numAviao = scanner.nextInt();
        boolean busca = false;
        for (int i = 0; i < 4; i++) {
            if (numAviao == aviao[i]) {
                busca = true;
                if (assentos[i] > 0) {
                    System.out.println("Informe o nome do passageiro:");
                    String nome = scanner.next();
                    checkin[numReservas] = new Reserva(numAviao, nome);
                    assentos[i]--;
                    System.out.println("Reserva realizada com sucesso!");
                    numReservas++;
                } else {
                    System.out.println("Não há assentos disponíveis para este avião!");
                }
            }
        }
        if (!busca) {
            System.out.println("Avião não existente");
        }
    }

    static void consultaAviao() {
        System.out.println("Digite o numero do avião");
        int numAviao = scanner.nextInt();
        boolean disponibilidade = false;
        for (int i = 0; i < 4; i++) {
            if (numAviao == aviao[i]) {
                System.out.println("Avião " + aviao[i] + " tem o total de: " + assentos[i] + " Assentos disponíveis");
                for (int j = 0; j < numReservas; j++) {
                    if (checkin[j].nr_aviao == aviao[i]) {
                        System.out.println("Reserva feita por " + checkin[j].nomePassageiro + " para o Avião " + aviao[i]);
                    }
                }
                disponibilidade = true;
            }
        }
        if (!disponibilidade) {
            System.out.println("Avião não encontrado!");
        }
    }

    static void consultaPassageiro() {
        System.out.println("Digite o nome do passageiro");
        String nome = scanner.next();
        boolean disponibilidade = false;
        for (int j = 0; j < numReservas; j++) {
            if (checkin[j].nomePassageiro.equals(nome)) {
                System.out.println("Reserva feita por " + checkin[j].nomePassageiro + " para o Avião " + checkin[j].nr_aviao);
                disponibilidade = true;
            }
        }
        if (!disponibilidade) {
            System.out.println("Não há reservas realizadas para este passageiro!");
        }
    }

    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static class Reserva {
        int nr_aviao;
        String nomePassageiro;

        Reserva(int nr_aviao, String nomePassageiro) {
            this.nr_aviao = nr_aviao;
            this.nomePassageiro = nomePassageiro;
        }
    }
}
