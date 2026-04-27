   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;

public class Calculadora {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Calculadora - Íres");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Campo de texto
        JTextField campo = new JTextField();
        campo.setBackground(Color.BLACK);
        campo.setForeground(Color.WHITE);
        campo.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(campo, BorderLayout.NORTH);

        // Painel dos botões
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 4));

        String[] botoes = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0","C","=","+"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setBackground(Color.DARK_GRAY);
            botao.setForeground(Color.WHITE);

            botao.setFont(new Font("Arial", Font.BOLD, 20));

            botao.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String comando = e.getActionCommand();

                    if (comando.equals("C")) {
                        campo.setText("");
                    } 
                    else if (comando.equals("=")) {
                        try {
                            String expressao = campo.getText();
                            double resultado = calcular(expressao);
                            campo.setText(String.valueOf(resultado));
                        } catch (Exception ex) {
                            campo.setText("Erro na conta");
                        }
                    } 
                    else {
                        campo.setText(campo.getText() + comando);
                    }
                }
            });

            painel.add(botao);
        }

        frame.add(painel);
        frame.setVisible(true);
    }

    // Método que calcula expressão simples (ex: 5+3)
    public static double calcular(String exp) {

        double num1 = 0;
        double num2 = 0;
        char operador = ' ';

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operador = c;
                num1 = Double.parseDouble(exp.substring(0, i));
                num2 = Double.parseDouble(exp.substring(i + 1));
                break;
            }
        }

        switch (operador) {
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            case '*': return num1 * num2;
            case '/':
    if (num2 == 0) {
        throw new ArithmeticException("Divisão por zero");
    }
    return num1 / num2;
        }

        return 0;
    }
}
