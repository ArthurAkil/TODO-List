package classes;


import enums.Categoria;
import enums.Prioridade;
import enums.Status;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Condition;

public class ConjuntoTarefas {
    TreeSet<Tarefa> tarefas = new TreeSet<>(Comparator
            .comparingInt((Tarefa t) -> t.getPrioridade().getNivel())
            .reversed()
            .thenComparing((Tarefa t) -> t.getId()));

    public void salvarTarefas(String caminho){
        try(BufferedWriter escreva = new BufferedWriter(new FileWriter(caminho))){
            for (Tarefa tarefa : tarefas){
                escreva.write(
                        tarefa.getId() + ";" + tarefa.getNome() + ";" + tarefa.getDescricao() + ";" + tarefa.getDataTermino() + ";" + tarefa.getPrioridade() + ";" +tarefa.getCategoria() + ";" + tarefa.getStatus() + ";" + tarefa.getDataCriacao()
                );
                escreva.newLine();
                }
            } catch (IOException e) {
            System.out.println("Erro ao salvar aquivo" + e.getMessage());
        }
    }

    public void carregarTarefas(String caminho){
        try(BufferedReader leia = new BufferedReader(new FileReader(caminho))){
            String linha;

            while((linha = leia.readLine()) != null){
                String[] atributos = linha.split(";");

                Tarefa tarefa = new Tarefa(atributos[1], atributos[2], LocalDate.parse(atributos[3]), Prioridade.valueOf(atributos[4]), Categoria.valueOf(atributos[5]), Status.valueOf(atributos[6]));

                tarefa.setDataCriacao(LocalDate.parse(atributos[7]));

                tarefas.add(tarefa);
            }
        } catch (IOException e){
            System.out.println("Erro ao carregar arquivo" + e.getMessage());
        }
    }

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
