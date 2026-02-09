package classes;

import enums.Categoria;
import enums.Prioridade;
import enums.Status;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuControle {
    private Scanner sc = new Scanner(System.in);
    private boolean app = true;

    public void iniciar(ConjuntoTarefas tarefas) {
        while (app) {
            menuPrincipal(tarefas);
        }
    }

    public void menuPrincipal(ConjuntoTarefas tarefas) {
        System.out.println("Você possui " + tarefas.getTarefas().size() + " tarefas cadastradas. Deseja vê-las? [1] - Sim / [2] - Não");
        int escolha = sc.nextInt();
        sc.nextLine();
        switch (escolha) {
            case 1:
                segundoMenu(tarefas);


                // resto...


                break;
            case 2:
                this.app = false;
                break;
            default:
                System.out.println("Selecione uma opção válida.");
                break;
        }
    }

    public void segundoMenu(ConjuntoTarefas tarefas) {
        System.out.println("Aqui estão todas as suas tarefas cadastradas: ");
        tarefas.listarTarefas();
        System.out.println("-=-");
        List<Tarefa> listaBase = menuFiltragem(tarefas);
        List<Tarefa> listaFinal =  menuOrdenacao(listaBase);
        tarefas.listarTarefas(listaFinal);

    }

    public void menuCrud(ConjuntoTarefas tarefas){
        System.out.println("Depois de analisar sua lista de tarefas você deseja: [1] - Ler tarefa específica / [2] - Editar tarefa específica / [3] - Criar tarefa nova / [4] - Deletar tarefa ");

        int escolhaCrud = sc.nextInt();
        sc.nextLine();

        switch (escolhaCrud){
            case 1:
                System.out.println("Digite o id específico da tarefa que você deseja ver: ");
                int escolhaIndice = sc.nextInt();
                tarefas.verTarefa(escolhaIndice);
                break;
            case 2:

        }

    }

    public List<Tarefa> menuFiltragem(ConjuntoTarefas tarefas) {
        System.out.println("Pelo o que você prefere filtrar sua lista de tarefas: [1] - Categoria / [2] - Prioridade / [3] - Status / [4] - Nenhuma (ver lista completa)");
        int escolhaFiltro = sc.nextInt();
        sc.nextLine();
        List<Tarefa> listaFiltrada;

        switch (escolhaFiltro) {
            case 1:
                return tarefas.filtrarCategoria(categoriaEscolhida());
            case 2:
                return tarefas.filtrarPrioridade(prioridadeEscolhida());
            case 3:
                return tarefas.filtrarStatus(statusEscolhida());
            default:
                return new ArrayList<>(tarefas.getTarefas());
        }
    }

    public List<Tarefa> menuOrdenacao(List<Tarefa> lista){
        System.out.println("Por padrão, suas tarefas já estoa organizadas por PRIORIDADE, aqui você define se quer manter essa ordenação ou deseja alterar por: [1] - Data de criação / [2] - Data de encerramento / [3] - Manter padrão");

        int escolhaOrdenacao = sc.nextInt();
        sc.nextLine();

        switch (escolhaOrdenacao){
            case 1:
                return lista.stream().sorted(Comparator.comparing(tarefa -> tarefa.getDataCriacao())).toList();
            case 2:
                return lista.stream().sorted(Comparator.comparing(tarefa -> tarefa.getDataTermino())).toList();
            default:
                return lista;

        }
    }

    public Categoria categoriaEscolhida() {
        while (true){
            System.out.println("Vamos filtrar por determinada categoria então, qual categoria deseja escolher? [1] - Trabalho / [2] - Estudo / [3] - Pessoal / [4] - Financeiro");
            int escolhaCategoria = sc.nextInt();
            sc.nextLine();
            switch (escolhaCategoria) {
                case 1:
                    return Categoria.TRABALHO;
                case 2:
                    return Categoria.ESTUDO;
                case 3:
                    return Categoria.PESSOAL;
                case 4:
                    return Categoria.FINANCEIRO;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public Prioridade prioridadeEscolhida() {
        while (true){
            System.out.println("Vamos filtrar por determinada prioridade então, qual prioridade deseja escolher? [1] - Muito Baixa / [2] - Baixa / [3] - Media / [4] - Alta / [5] - Muito alta");
            int escolhaPrioridade = sc.nextInt();
            sc.nextLine();
            switch (escolhaPrioridade) {
                case 1:
                    return Prioridade.MUITO_BAIXA;
                case 2:
                    return Prioridade.BAIXA;
                case 3:
                    return Prioridade.MEDIA;
                case 4:
                    return Prioridade.ALTA;
                case 5:
                    return Prioridade.MUITO_ALTA;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public Status statusEscolhida() {
        while (true){
            System.out.println("Vamos filtrar por determinado status então, qual status deseja escolher? [1] - TODO / [2] - DOING / [3] - DONE ");
            int escolhaStatus = sc.nextInt();
            sc.nextLine();
            switch (escolhaStatus) {
                case 1:
                    return Status.TODO;
                case 2:
                    return Status.DOING;
                case 3:
                    return Status.DONE;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }



}
