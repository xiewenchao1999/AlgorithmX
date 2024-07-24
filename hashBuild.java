import java.util.Arrays;

public class hashBuild {
    public static void main(String[] args) {
        int num = 3;
        int hashNum = Integer.hashCode(num);
        System.out.println(num + "-> hashNum = " + hashNum);

        boolean bol = true;
        int hashBol = Boolean.hashCode(bol);
        System.out.println(bol + "-> hashBol = " + hashBol);

        double dec = 3.14159;
        int hashDec = Double.hashCode(dec);
        System.out.println(dec + "-> hashDec = " + hashDec);

        String str = "Hello World!";
        int hashStr = str.hashCode();
        System.out.println(str + "-> hashStr = " + hashStr);

        Object[] arr = { 12836, "哈哈"};
        int hashTup = Arrays.hashCode(arr);
        System.out.println(arr + "-> hashTup = " + hashTup);

        ListNode obj = new ListNode(0);
        int hashObj = obj.hashCode();
        System.out.println(obj + "-> hashObj = " + hashObj);
    }
}
