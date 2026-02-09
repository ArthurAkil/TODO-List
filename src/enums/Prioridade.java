package enums;

public enum Prioridade {
    MUITO_BAIXA(1),
    BAIXA(2),
    MEDIA(3),
    ALTA(4),
    MUITO_ALTA(5);

    private final int nivel;

    Prioridade(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
