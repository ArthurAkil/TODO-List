import classes.ConjuntoTarefas;
import classes.Tarefa;
import enums.Prioridade;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Tarefa t1 = new Tarefa("Tarefa1", Prioridade.MUITO_BAIXA);
        Tarefa t2 = new Tarefa("Tarefa2",Prioridade.BAIXA);
        Tarefa t3 = new Tarefa("Tarefa3",Prioridade.MEDIA);
        Tarefa t4 = new Tarefa("Tarefa4",Prioridade.ALTA);
        Tarefa t5 = new Tarefa("Tarefa5",Prioridade.MUITO_ALTA);

        ConjuntoTarefas conjunto = new ConjuntoTarefas();

        conjunto.adicionarTarefa(t1);
        conjunto.adicionarTarefa(t2);
        conjunto.adicionarTarefa(t3);
        conjunto.adicionarTarefa(t4);
        conjunto.adicionarTarefa(t5);

        conjunto.listarTarefas();
    }
}