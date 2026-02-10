package classes;

import enums.Categoria;
import enums.Prioridade;
import enums.Status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        menuCrud(tarefas);
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
                System.out.println("Digite o id da tarefa que deseja editar: ");
                int idEditar = sc.nextInt();
                sc.nextLine();
                menuEditar(tarefas, idEditar);
                break;

            case 3:
                System.out.println("Vamos criar a tarefa: ");
                menuCriar(tarefas);
                break;
            case 4:
                System.out.println("Digite o id da tarefa que você deseja excluir: ");
                int idExcluir = sc.nextInt();
                sc.nextLine();
                tarefas.removerTarefa(idExcluir);
                System.out.println("Se existiu alguma tarefa com aquela id, foi deletado com sucesso.");
                break;
        }
    }

    public void menuCriar(ConjuntoTarefas tarefas){
        System.out.println("Para criar uma tarefa nova vamos definir seus atributos: ");
            try{
                System.out.println("Defina o nome da sua tarefa: ");
                String nome = sc.nextLine();

                System.out.println("Defina a descrição da tarefa: ");
                String descricao = sc.nextLine();

                System.out.println("Defina a data de encerramento da tarefa. Exemplo 02/01/2029: ");
                LocalDate termino = transformarData();

                System.out.println("Defina a prioridade: ");
                Prioridade prioridade = prioridadeEscolhida();

                System.out.println("Defina a categoria: ");
                Categoria categoria = categoriaEscolhida();

                System.out.println("Defina o status: ");
                Status status = statusEscolhida();

                Tarefa t = new Tarefa(nome, descricao, termino, prioridade, categoria, status);

                tarefas.getTarefas().add(t);
            } catch (Exception e){
                System.out.println("Ops, algo deu errado, tente novamente.");
            }
    }

    public void menuEditar(ConjuntoTarefas tarefas, int id){
        Tarefa t = tarefas.buscarId(id);

        if (t == null){
            System.out.println("Tarefa não encontrada");
            return;
        }

        System.out.println("O que deseja editar dessa tarefa: [1] - Nome / [2] - Descrição / [3] - Data de término / [4] - Prioridade / [5] - Categoria / [6] - Status / [0] - Voltar");
        int editEscolha = sc.nextInt();
        sc.nextLine();

        tarefas.getTarefas().remove(t);
        switch (editEscolha){
            case 1:
                System.out.println("Novo nome: ");
                String nomeNovo = sc.nextLine();
                t.setNome(nomeNovo);
                break;
            case 2:
                System.out.println("Descrição nova: ");
                String descricaoNova = sc.nextLine();
                t.setDescricao(descricaoNova);
                break;
            case 3:
                System.out.println("Digite a nova data no formato 'dia/mes/ano': ");
                t.setDataTermino(transformarData());
                break;
            case 4:
                System.out.println("Escolha a nova prioridade: ");
                t.setPrioridade(prioridadeEscolhida());
                break;
            case 5:
                System.out.println("Escolha a nova categoria: ");
                t.setCategoria(categoriaEscolhida());
                break;
            case 6:
                System.out.println("Escolha o novo status: ");
                t.setStatus(statusEscolhida());
                break;
            case 0:
                System.out.println("Voltando sem alterações");
                tarefas.getTarefas().add(t);
                return;
            default:
                System.out.println("Opção inválida");
                break;
        }
        tarefas.getTarefas().add(t);
        System.out.println("Tarefa atualizada");
    }

    public List<Tarefa> menuFiltragem(ConjuntoTarefas tarefas) {
        System.out.println("Pelo o que você prefere filtrar sua lista de tarefas: [1] - Categoria / [2] - Prioridade / [3] - Status / [4] - Nenhuma (ver lista completa)");
        int escolhaFiltro = sc.nextInt();
        sc.nextLine();

        switch (escolhaFiltro) {
            case 1:
                System.out.println("Escolha uma das categorias que deseja filtrar: ");
                return tarefas.filtrarCategoria(categoriaEscolhida());
            case 2:
                System.out.println("Escolha uma das prioridades que deseja filtrar: ");
                return tarefas.filtrarPrioridade(prioridadeEscolhida());
            case 3:
                System.out.println("Escolha um dos status que deseja filtrar: ");
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

    public LocalDate transformarData(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while(true){
            String data = sc.nextLine().trim();

            try{
                return LocalDate.parse(data, formato);
            } catch (DateTimeParseException e){
                System.out.println("Digite a data no formato correto, ex: 09/02/2020");
            }
        }
    }

    public Categoria categoriaEscolhida() {
        while (true){
            System.out.println("[1] - Trabalho / [2] - Estudo / [3] - Pessoal / [4] - Financeiro");
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
            System.out.println("[1] - Muito Baixa / [2] - Baixa / [3] - Media / [4] - Alta / [5] - Muito alta");
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
            System.out.println("[1] - TODO / [2] - DOING / [3] - DONE ");
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
