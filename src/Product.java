import java.io.Serializable;

public class Product implements Serializable {
    static final long serialVersionUID = 1L;

    private int idProduct;
    private String name;
    private double prince;
    private int numProduct;
    private String description;

    public Product() {
    }

    public Product(int  idProduct, String name, double prince, int numProduct, String description) {
        this.idProduct = idProduct;
        this.name = name;
        this.prince = prince;
        this.numProduct = numProduct;
        this.description = description;
    }

    public Product(String datum, String datum1, double prince, int numProduct, String datum2) {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrince() {
        return prince;
    }

    public void setPrince(double prince) {
        this.prince = prince;
    }

    public int getNumProduct() {
        return numProduct;
    }

    public void setNumProduct(int numProduct) {
        this.numProduct = numProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void display() {
        System.out.printf("%-15s%-35s%-35s%-35s%-35s%s",
                this.idProduct, this.name, this.prince, this.numProduct, this.description,"\n");
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct='" + idProduct + '\'' +
                ", name='" + name + '\'' +
                ", prince=" + prince +
                ", numProduct=" + numProduct +
                ", description='" + description + '\'' +
                '}';
    }

    public int writeFileCsv() {
        return 0;
    }
}
