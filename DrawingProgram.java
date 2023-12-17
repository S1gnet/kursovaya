import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingProgram extends JFrame {
    private int numOfLines, numOfCircles, numOfRectangles, numOfTriangles, numOfParabolas, numOfTrapezoids;

    public DrawingProgram() {
        setTitle("Drawing Program");
        setSize(800, 800); // Увеличиваем размер окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        JButton drawButton = new JButton("Рисовать фигуры");
        panel.add(drawButton);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Отрисовка фигур здесь
                repaint();
                // Вывод среднего расстояния между фигурами
                double averageDistance = calculateAverageDistance();
                JOptionPane.showMessageDialog(null, "Кучность фигур: " + averageDistance/10);
            }
        });

        // Добавление диалоговых окон для ввода количества фигур
        numOfLines = getValidInput("Введите количество линий:");
        numOfCircles = getValidInput("Введите количество окружностей:");
        numOfRectangles = getValidInput("Введите количество прямоугольников:");
        numOfTriangles = getValidInput("Введите количество треугольников:");
        numOfParabolas = getValidInput("Введите количество парабол:");
        numOfTrapezoids = getValidInput("Введите количество трапеций:");
    }

    private int getValidInput(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(message);
                if (input == null) {
                    // Пользователь нажал "Отмена" или закрыл диалоговое окно
                    System.exit(0);
                }

                int value = Integer.parseInt(input);

                if (value < 0) {
                    throw new NumberFormatException("Введите положительное число или 0.");
                }

                return value;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ошибка ввода: " + e.getMessage());
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Рисование полупрозрачной координатной сетки
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Устанавливаем полупрозрачность
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        // Отрисовка координатной сетки
        g2d.setColor(Color.GRAY);
        for (int i = -200; i <= 200; i += 10) {
            int x = centerX + i * 4; // Увеличиваем масштаб
            g2d.drawLine(x, 0, x, getHeight());
        }

        for (int i = -100; i <= 100; i += 10) {
            int y = centerY - i * 4; // Увеличиваем масштаб
            g2d.drawLine(0, y, getWidth(), y);
        }

        // Очищаем графический контекст
        g2d.dispose();

        // Рисование координатной плоскости
        g.setColor(Color.BLACK);
        g.drawLine(0, centerY, getWidth(), centerY);
        for (int i = -200; i <= 200; i += 10) {
            int x = centerX + i * 4; // Увеличиваем масштаб
            g.drawLine(x, centerY - 5, x, centerY + 5);
            g.drawString(Integer.toString(i), x - 5, centerY + 20);
        }

        g.drawLine(centerX, 0, centerX, getHeight());
        for (int i = -100; i <= 100; i += 10) {
            int y = centerY - i * 4; // Увеличиваем масштаб
            g.drawLine(centerX - 5, y, centerX + 5, y);
            g.drawString(Integer.toString(i), centerX + 10, y + 5);
        }

        // Отрисовка фигур
        // Отрисовка линий
        for (int i = 0; i < numOfLines; i++) {
            int x1 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y1 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int x2 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y2 = (int) (Math.random() * 200) - 100; // От -100 до 100
            g.drawLine(centerX + x1 * 4, centerY - y1 * 4, centerX + x2 * 4, centerY - y2 * 4);
        }

        // Отрисовка окружностей
        for (int i = 0; i < numOfCircles; i++) {
            int x = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y = (int) (Math.random() * 200) - 100; // От -100 до 100
            g.drawOval(centerX + x * 4, centerY - y * 4, 50, 50);
        }

        // Отрисовка прямоугольников
        for (int i = 0; i < numOfRectangles; i++) {
            int x = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y = (int) (Math.random() * 200) - 100; // От -100 до 100
            g.drawRect(centerX + x * 4, centerY - y * 4, 60, 40);
        }

        // Отрисовка треугольников
        for (int i = 0; i < numOfTriangles; i++) {
            int x1 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y1 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int x2 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y2 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int x3 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y3 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int[] xPoints = {centerX + x1 * 4, centerX + x2 * 4, centerX + x3 * 4};
            int[] yPoints = {centerY - y1 * 4, centerY - y2 * 4, centerY - y3 * 4};
            g.drawPolygon(xPoints, yPoints, 3);
        }

        // Отрисовка парабол
        for (int i = 0; i < numOfParabolas; i++) {
            int a = (int) (Math.random() * 20) - 10; // Коэффициент a для параболы
            int x;
            int y1, y2;
            for (int j = -100; j <= 100; j++) {
                x = centerX + j * 4;
                y1 = centerY - a * j * j / 16; // Увеличиваем масштаб
                y2 = centerY - a * (j + 1) * (j + 1) / 16; // Увеличиваем масштаб
                g.drawLine(x, y1, x + 4, y2); // Соединяем точки линиями
            }
        }

        // Отрисовка трапеций
        for (int i = 0; i < numOfTrapezoids; i++) {
            int x1 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y1 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int baseWidth = (int) (Math.random() * 100) + 50; // Базовая ширина трапеции
            int height = (int) (Math.random() * 40); // Высота трапеции
            int x2 = x1 + baseWidth;
            int x3 = x1 + (baseWidth - height) / 2;
            int x4 = x2 - (baseWidth - height) / 2;
            int[] xPoints = {centerX + x1 * 4, centerX + x2 * 4, centerX + x4 * 4, centerX + x3 * 4};
            int[] yPoints = {centerY - y1 * 4, centerY - y1 * 4, centerY - (y1 + height) * 4, centerY - (y1 + height) * 4};
            g.drawPolygon(xPoints, yPoints, 4);
        }
    }

    private double calculateAverageDistance() {
        int totalPoints = numOfLines * 2 + numOfCircles + numOfRectangles * 2 + numOfTriangles * 3 + numOfParabolas * 201 + numOfTrapezoids * 4;
        int totalDistance = 0;

        for (int i = 0; i < totalPoints; i++) {
            int x1 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y1 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int x2 = (int) (Math.random() * 200) - 100; // От -100 до 100
            int y2 = (int) (Math.random() * 200) - 100; // От -100 до 100
            totalDistance += Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        }

        return totalDistance / totalPoints;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrawingProgram().setVisible(true);
            }
        });
    }
}
