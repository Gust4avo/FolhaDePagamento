package folhaDePagamento;

import java.text.DecimalFormat;
import java.util.Scanner;

public class FolhaDePagamento {
    
    // Calcula o Imposto de Renda Retido na Fonte (IRRF)
    public static double calcIRPF(double salario) {
        double IRPF;
        if (salario <= 1903.98) {
            IRPF = 0;
        } else if (salario <= 2826.65) {
            IRPF = (salario * 0.075 ) - 169.44;
        } else if (salario <= 3751.05) {
            IRPF = (salario * 0.15 ) - 381.44;
        } else if (salario <= 4664.68) {
            IRPF = (salario * 0.225) - 662.77;
        } else {
            IRPF = (salario * 0.275 ) - 896; 
        }
        return IRPF;
    }
    
    // Calcula o Instituto Nacional do Seguro Social (INSS)
    public static double calcINSS(double salario) {
        double INSS;
        if (salario <= 1412) {
            INSS = salario * 0.075;
        } else if (salario <= 2666.68) {
            INSS = ((salario - 1412) * 0.09) + 105.90;
        } else if (salario <= 4000.03) {
            INSS = ((salario - 2666.69) * 0.12) + 105.90 + 112.92;
        } else if (salario <= 7786.02) {
            INSS = ((salario - 4000.03) * 0.14) + 105.90 + 112.92 + 160;
        } else {
            INSS = ((7786.02 - 4000.03) * 0.14) + 105.90 + 112.92 + 160;
        }
        return INSS;
    }

    // Calcula o desconto ou horas extras
    public static double calcDescExtra(double horasTrab, double salario) {
        double salarioHora = salario / 160;
        double descontoExtra = (horasTrab - 160) * salarioHora;
        return descontoExtra;
    }
    
    public static void main(String[] args) {
        Scanner resp = new Scanner(System.in); 
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("R$ #,##0.00");
        
        // Entrada de dados do usuário
        System.out.println("* FOLHA DE PAGAMENTO *");
        System.out.println("Digite o seu nome: ");
        String nome = resp.next();
        System.out.println("Digite seu salario bruto mensal: ");
        double salario = resp.nextDouble();
        System.out.println("Digite o total de horas trabalhadas por mês: ");
        double horasTrab = resp.nextDouble();
    
        System.out.println("* Calculo da folha de pagamento *");
        
        System.out.println("colaborador: " + nome);
        System.out.println("salario bruto: " + salario);
        System.out.println("IRRF retido: " + df.format(calcIRPF(salario)));
        System.out.println("INSS retido: " + df.format(calcINSS(salario)));
        
        if (horasTrab < 160) {
            System.out.println("desconto por atraso: " + df.format(calcDescExtra(horasTrab, salario)));
        }
        if (horasTrab > 160) {
            System.out.println("hora extra a receber: " + df.format(calcDescExtra(horasTrab, salario)));
        }
        double salarioLiquido = salario - calcIRPF(salario) - calcINSS(salario) + calcDescExtra(horasTrab, salario);
        System.out.println("salario liquido: " + df.format(salarioLiquido));
        
        resp.close();
    }
}