package classes;


import java.util.*;

public class ConjuntoTarefas {
    TreeSet<Tarefa> tarefas = new TreeSet<>(Comparator
            .comparingInt((Tarefa t) -> t.getPrioridade().getNivel())
            .reversed()
            .thenComparing((Tarefa t) -> t.getDataTermino())
            .thenComparing((Tarefa t) -> t.getNome())
            .thenComparing((Tarefa t) -> t.getDescricao()));


    public void adicionarTarefa(Tarefa t){
        this.tarefas.add(t);
    }

    public void listarTarefas(){
        List<Tarefa> lista = new ArrayList<>(tarefas);
        System.out.println("-=- Listagem de tarefas -=-");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " " +lista.get(i).printInfoTarefa());
        }
    }

    public TreeSet<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(TreeSet<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
