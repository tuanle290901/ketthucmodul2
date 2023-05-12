import java.io.*;
import java.util.*;

public class ProductManage implements ProductInterface<Product> {
    Scanner scanner = new Scanner(System.in);
    List<Product> products = new ArrayList<>();


    public void addProduct1() {
        Product product1 = new Product(1, "táo", 15000, 12, "táo tàu");
        Product product2 = new Product(2, "chuối", 16000, 10, "chuối tây");
        Product product3 = new Product(3, "dưa", 13000, 11, "dưa ta");
        Product product4 = new Product(4, "ổi", 19000, 12, "Ổi");
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);


    }


    public ProductManage() {

    }


    @Override
    public void disPlayAll() {
        System.out.printf("%-15s%-35s%-35s%-35s%s",
                "Mã sản phẩm", "Tên sản phẩm", "Giá sản phẩm", "Số lượng sản phẩm", "mô tả sản phẩm\n");

        for (Product p : products) {
            p.display();
        }
    }

    @Override
    public void addProduct() {
        int idProduct = checkDefaultIndex() + 1;
        ;
        System.out.println(" Mời bạn nhập tên sản phẩm");
        String name = scanner.nextLine();
        double prince;
        while (true) {
            System.out.println(" Mời bạn nhập giá sản phẩm");
            try {
                prince = Double.parseDouble(scanner.nextLine());
                if (prince < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        int numProduct;
        while (true) {
            System.out.println(" Mời bạn nhập số lượng sản phẩm");
            try {
                numProduct = Integer.parseInt(scanner.nextLine());
                if (numProduct < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(" Mô tả sản phẩm");
        String description = scanner.nextLine();
        Product product = new Product(idProduct, name, prince, numProduct, description);
        products.add(product);
        writeFileProduct();

    }


    @Override
    public void updateProduct() {
        System.out.println("Danh sách sản phẩm:");
        disPlayAll();
        Product product = getById();
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm.");
            return;
        }

        System.out.println("Nhập tên sản phẩm mới (nhấn Enter để bỏ qua):");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            product.setName(name);
        }

        System.out.println("Nhập giá sản phẩm mới (nhấn Enter để bỏ qua):");
        String priceStr = scanner.nextLine();
        if (!priceStr.isEmpty()) {
            try {
                double price = Double.parseDouble(priceStr);
                if (price >= 0) {
                    product.setPrince(price);
                } else {
                    System.out.println("Giá sản phẩm phải lớn hơn hoặc bằng 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá sản phẩm không hợp lệ.");
            }
        }
        System.out.println("Nhập số lượng sản phẩm mới (nhấn Enter để bỏ qua):");
        String numProductStr = scanner.nextLine();
        if (!numProductStr.isEmpty()) {
            try {
                int numProduct = Integer.parseInt(numProductStr);
                if (numProduct >= 0) {
                    product.setNumProduct(numProduct);
                } else {
                    System.out.println("Số lượng sản phẩm phải lớn hơn hoặc bằng 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Số lượng sản phẩm không hợp lệ.");
            }
        }
        System.out.println("Nhập mô tả sản phẩm mới (nhấn Enter để bỏ qua):");
        String description = scanner.nextLine();
        if (!description.isEmpty()) {
            product.setDescription(description);
        }
        System.out.println("Sản phẩm sau khi cập nhật:");
        product.display();
        writeFileProduct();
    }


    @Override
    public void dellProduct() {
        Product product = getById();
        if (product != null) {
            products.remove(product);
        }
        assert product != null;
        product.display();
        writeFileProduct();
    }


    @Override
    public void arrangeProductPriceIncrease() {
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrince() > o2.getPrince()) return 1;
                else if (o1.getPrince() < o2.getPrince()) return -1;
                else return 0;
            }
        });

        System.out.printf("%-15s%-35s%-35s%-35s%s",
                "Mã sản phẩm", "Tên sản phẩm", "Giá sản phẩm", "Số lượng sản phẩm", "Mô tả sản phẩm\n");
        for (Product p : products) {
            p.display();
        }
    }

    public void arrangeProductPriceIncreaseDecrease() {
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrince() < o2.getPrince()) return 1;
                else if (o1.getPrince() > o2.getPrince()) return -1;
                else return 0;
            }
        });
        System.out.printf("%-15s%-35s%-35s%-35s%s",
                "Mã sản phẩm", "Tên sản phẩm", "Giá sản phẩm", "Số lượng sản phẩm", "Mô tả sản phẩm\n");
        for (Product p : products) {
            p.display();
        }
    }


    @Override
    public void findProduct() {
        double max = 0;
        for (Product p : products) {
            if (p.getPrince() > max) {
                max = p.getPrince();
            }
        }
        for (Product p : products) {
            if (p.getPrince() == max) {
                System.out.println("Sản phẩm có giá đắt nhất là ");
                p.display();
            }
        }

    }

    public Product getById() {
        int id;
        do {
            try {
                System.out.println("Nhập lựa chọn của bạn: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn không phù hợp, mời bạn chọn lại!");
            }
        } while (true);
        for (Product product : products) {
            if (product.getIdProduct() == id) {
                return product;
            }
        }
        return null;
    }

    private int checkDefaultIndex() {
        if (products.isEmpty()) {
            return 0;
        } else {
            return products.get(products.size() - 1).getIdProduct();
        }
    }

    public void readFileProduct() {
        File file = new File("D:\\modul_2\\modul2_\\src\\Product.txt");

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                String[] arrProduct = string.split(",");
                Product product = new Product(Integer.parseInt(arrProduct[0]), arrProduct[1], arrProduct[2],
                        Double.parseDouble(arrProduct[3]), Integer.parseInt(arrProduct[4]), arrProduct[5]);
                products.add(product);
            }
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println();
        }

    }

    public void writeFileProduct() {
        File file = new File("D:\\modul_2\\modul2_\\src\\Product.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Product p : products) {
                bufferedWriter.write(p.toString() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

