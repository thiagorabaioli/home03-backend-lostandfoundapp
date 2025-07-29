package tfr.LostAndFoundAPP.entities.enums;

public enum TYPEOFINTERACTION {

    INSERT(1, "INSERT"),
    DELIVERY(2, "DELIVERY"),
    WAITING(2, "WAITING"),
    OTHER(3, "Other");

    private int cod;
    private String description;

    private TYPEOFINTERACTION(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }
    public int getCod() {
        return cod;
    }
    public String getDescription() {
        return description;
    }

    public static TYPEOFINTERACTION toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TYPEOFINTERACTION x : TYPEOFINTERACTION.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalid: " + cod);
    }

}
