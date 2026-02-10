package classes;


import enums.Categoria;
import enums.Prioridade;
import enums.Status;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Condition;

public class ConjuntoTarefas {
    TreeSet<Tarefa> tarefas = new TreeSet<>(Comparator
            .comparingInt((Tarefa t) -> t.getPrioridade().getNivel())
            .reversed()
            .thenComparing((Tarefa t) -> t.getId()));


    public void adicionarTarefa(Tarefa t){
        this.tarefas.add(t);
    }

    public void removerTarefa(int id){
        tarefas.removeIf(tarefa -> tarefa.getId() == id);
    }

    public void editarTarefa(){

    }

    public Tarefa buscarId(int id){
        for (Tarefa t : tarefas){
            if (t.getId() == id){
                return t;
            }
        }
        return null;
    }

    public List<Tarefa> filtrarCategoria(Categoria c){
        return tarefas.stream()
                .filter(tarefa -> tarefa.getCategoria() == c)
                .toList();
    }

    public List<Tarefa> filtrarPrioridade(Prioridade p){
        return tarefas.stream()
                .filter(tarefa -> tarefa.getPrioridade() == p)
                .toList();
    }

    public List<Tarefa> filtrarStatus(Status s){
        return tarefas.stream()
                .filter(tarefa -> tarefa.getStatus() == s)
                .toList();
    }

    public List<Tarefa> filtrarDataFinal(){
        return tarefas.stream()
                .sorted(Comparator.comparing(tarefa -> tarefa.getDataTermino()))
                .toList();
    }

    public List<Tarefa> filtrarDataCriada(){
        return tarefas.stream()
                .sorted(Comparator.comparing(tarefa -> tarefa.getDataCriacao()))
                .toList();
    }

    public void verTarefa(int i){
        try{
            List<Tarefa> lista = new ArrayList<>(tarefas);
            for (Tarefa f : lista){
                if (f.getId() == i){
                    f.printTarefaEsp();
                }
            }
        } catch (Exception e){
            System.out.println("Indice invalido.");
        }
    }

    public void listarTarefas(){
        listarTarefas(new ArrayList<>(tarefas));
    }

    public void listarTarefas(List<Tarefa> lista){
        System.out.println("-=- Listagem de tarefas -=-");
        if (lista.isEmpty()){
            System.out.println("Nenhuma tarefa cadastrada até o momento.");
        }else{
            for (Tarefa t : lista){
                System.out.println(t.stringInfoTarefa());
            }
        }
        System.out.println("-=- =-= -=-");
    }

    public TreeSet<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(TreeSet<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
