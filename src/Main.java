import classes.ConjuntoTarefas;
import classes.MenuControle;
import classes.Tarefa;
import enums.Categoria;
import enums.Prioridade;
import enums.Status;

import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ConjuntoTarefas conjunto = new ConjuntoTarefas();

        conjunto.adicionarTarefa(new Tarefa(
                "Estudar Java",
                "Revisar Collections e Streams",
                LocalDate.of(2026, 2, 15),
                Prioridade.ALTA,
                Categoria.ESTUDO,
                Status.TODO
        ));

        conjunto.adicionarTarefa(new Tarefa(
                "Projeto tarefas",
                "Implementar CRUD no terminal",
                LocalDate.of(2026, 2, 20),
                Prioridade.MUITO_ALTA,
                Categoria.TRABALHO,
                Status.DOING
        ));

        MenuControle menu = new MenuControle();
        menu.iniciar(conjunto);

    }
}