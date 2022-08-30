import java.util.List;
import java.util.Scanner;

public class calc {
    public static void main(String[] args){

       //Переменные для расчетов
        int a,b,check,c,arabica,arabicb;
        String romaa,romab;
        romaa="";
        romab="";
        arabica=0;
        arabicb=0;
        char option='0';
        a=0;
        b=0;
        check=0;
        // Используем класс Scanner для ввода строки с клавиатуры
        Scanner in = new Scanner(System.in);
        Result result1 = new Result();
        //Ввод строки с клавитуры
        System.out.println("Введите пример:");String calculation = in.next();
        for (int j = 0; j<calculation.length();j++){
            if(calculation.charAt(j)=='+'||calculation.charAt(j)=='*'||calculation.charAt(j)=='-'||calculation.charAt(j)=='/'){
                option = calculation.charAt(j);
            }
        }
        //Разделим строчку на строки из чисел
        String [] strings = calculation.split("\\+|\\-|\\*|\\/");
        for (String element:strings) {
            check++;
            if(check==1){
                for (int i=0;i<element.length();i++) {
                    a=a*10+(int)(element.charAt(i))-'0';
                    romaa=romaa+element.charAt(i);
                }
            }
            if (check==2){
                for (int i=0;i<element.length();i++) {
                    b=b*10+(int)(element.charAt(i))-'0';
                    romab=romab+element.charAt(i);
                }
            }
            if (check>2){
                System.out.println("throws Exception");
                System.exit(1);
            }
        }

        RomanToArabic conversion = new RomanToArabic();
        conversion.romanNumeral=romaa;
        arabica=conversion.change();
        conversion.romanNumeral=romab;
        arabicb=conversion.change();
        if (arabica==0 || arabicb==0) {
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                System.out.println("throws Exception");
                System.exit(2);
            }
        }
        Result res = new Result();
        if (arabica!=0 && arabicb!=0){
            if (arabica<1||arabica>10||arabicb<1||arabicb>10){
                System.out.println("throws Exeption");
                System.exit(2);
            }
            res.a=arabica;
            res.b=arabicb;
            res.opt=option;
            c=res.result();
            ArabicToRoman conversion1 = new ArabicToRoman();
            conversion1.number=c;
            System.out.println("Ответ "+conversion1.change());

        } else{
            res.a=a;
            res.b=b;
            res.opt=option;
            c=res.result();
            System.out.println("Ответ "+ c);
        }
        in.close();
    }
}
class Result {
    int a, b, res1;
    char opt;
    int result(){
        switch (opt){
            case '+':
                res1=a+b;
                break;
            case '*':
                res1=a*b;
                break;
            case '/':
                res1=a/b;
                break;
            case '-':
                res1=a-b;
                break;
            default:
                System.out.println("throws Exception");
                System.exit(3);
        }
        return res1;
    }
}
class RomanToArabic{
    String romanNumeral;
    int result;
    List romanNumerals = RomanNum.getReverseSortedValues();
    int i=0;
    private String input;

    int change(){
        result=0;
        i=0;
        while ((romanNumeral.length()>0)&&(i<romanNumerals.size())){
            RomanNum symbol = (RomanNum) romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())){
                result+=symbol.getValue();
                romanNumeral=romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        if (romanNumeral.length()>0){
            return 0;
        }
        return result;
    }
}
class ArabicToRoman{
    int number,i;
    StringBuilder sb = new StringBuilder();
    List romanNumerals = RomanNum.getReverseSortedValues();
    String change(){
        while ((number>0)&&(i<romanNumerals.size())){
            RomanNum currentSymbol= (RomanNum) romanNumerals.get(i);
            if (currentSymbol.getValue()<=number){
                sb.append(currentSymbol.name());
                number-=currentSymbol.getValue();
            } else {
                i++;
            }
        }
        if (sb.toString()==""){
            System.out.println("throws Exception");
            System.exit(4);
        }
        return sb.toString();
    }
}