import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManage productManage = new ProductManage();
        productManage.addProduct1();
        Menu(productManage, scanner);
        productManage.readFileCsv();
    }

    public static void Menu(ProductManage productManage, Scanner scanner) {
        do {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ----");
            System.out.println("Chọn chức năng theo số ( để tiếp tục )");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Tìm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi vào file");
            System.out.println("9. Thoát");
            System.out.println(" Chọn chức năng");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 9) {
                    throw new IndexOutOfBoundsException(" Lựa chọn không lợp lệ, mời chọn lại menu");
                }
                switch (choice) {
                    case 1:
                        productManage.disPlayAll();
                        break;
                    case 2:
                        productManage.addProduct();
                        break;
                    case 3:
                        productManage.updateProduct();
                        break;
                    case 4:
                        productManage.dellProduct();
                        break;
                    case 5:
                        subMenu(productManage, scanner);
                        break;
                    case 6:
                        productManage.findProduct();
                        break;
                    case 7:
                        productManage.writeFileCsv();
                        break;
                    case 8:
                        productManage.readFileCsv();
                        break;
                    case 9:
                        System.exit(0);
                        break;

                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(" menu phải nhập số nguyên, mời chọn lại menu");
            }
        } while (true);

    }

    public static void subMenu(ProductManage productManage, Scanner scanner) {
        do {
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM ----");
            System.out.println("Chọn chức năng theo số ( để tiếp tục )");
            System.out.println("1. Sắp xếp sản phẩm theo giá tăng dần");
            System.out.println("2. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("0. thoát");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 2) {
                    throw new IndexOutOfBoundsException(" Lựa chọn không lợp lệ, mời chọn lại menu");
                }
                switch (choice) {
                    case 1:
                        productManage.arrangeProductPriceIncrease();
                        break;
                    case 2:
                            productManage.arrangeProductPriceIncreaseDecrease();
                        break;
                    case 0:
                        Menu(productManage, scanner);
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(" menu phải nhập số nguyên, mời chọn lại menu");
            }
        } while (true);

    }
}
