package org.example.Reg;

public class tel_3 {
    public static void main(String[] args) {
        String phone = "917-834-1214";
        String regex = "^\\d{3}-\\d{3}-\\d{4}$";

        if (phone.matches(regex)) {
            System.out.println("Корректный телефонный номер");
        } else {
            System.out.println("Некорректный телефонный номер");
        }


    }
}
