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

        conjunto.carregarTarefas("src/tarefas.txt");

        MenuControle menu = new MenuControle();
        menu.iniciar(conjunto);

        conjunto.salvarTarefas("src/tarefas.txt");

    }
}