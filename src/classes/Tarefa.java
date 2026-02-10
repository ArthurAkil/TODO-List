package classes;

import enums.Categoria;
import enums.Prioridade;
import enums.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private static int contador = 0;

    private final int id;
    private String nome;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataTermino;
    private Prioridade prioridade;
    private Categoria categoria;
    private Status status;

    public Tarefa(String nome, String descricao, LocalDate dataTermino, Prioridade prioridade, Categoria categoria, Status status) {
        this.id = ++contador;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = LocalDate.now();
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.status = status;
    }

    public void printTarefaEsp(){
        System.out.println();
        System.out.println("Detalhes tarefa: ");
        System.out.println("Id: " + this.id);
        System.out.println("Nome/Titulo: " + this.nome);
        System.out.println("Descrição: " +this.descricao);
        System.out.println("Data criação: " +this.dataCriacao.toString());
        System.out.println("Data termino: " +this.dataTermino.toString());
        System.out.println("Prioridade: " +this.prioridade.toString());
        System.out.println("Categoria: " +this.categoria.toString());
        System.out.println("Status: " +this.status.toString());
        System.out.println();
    }

    public String stringInfoTarefa(){
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Id: " + this.id + ", Nome: " + this.nome + ", Prioridade: " + this.prioridade.toString() + ", Data de encerramento: " +this.dataTermino.format(dataFormatada);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
