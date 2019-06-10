package com.company;

        import java.awt.AlphaComposite;
        import java.awt.BorderLayout;
        import java.awt.Color;
        import java.awt.Component;
        import java.awt.EventQueue;
        import java.awt.Font;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.RenderingHints;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.KeyEvent;
        import java.awt.geom.Ellipse2D;
        import java.awt.image.BufferedImage;
        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;
        import javax.swing.BorderFactory;
        import javax.swing.BoxLayout;
        import javax.swing.ButtonGroup;
        import javax.swing.Icon;
        import javax.swing.JButton;
        import javax.swing.JColorChooser;
        import javax.swing.JComboBox;
        import javax.swing.JFrame;
        import javax.swing.JLabel;
        import javax.swing.JPanel;
        import javax.swing.JRadioButton;
        import javax.swing.JSpinner;
        import javax.swing.JTextArea;
        import javax.swing.SpinnerNumberModel;
        import javax.swing.border.Border;
        import javax.swing.event.ChangeEvent;
        import javax.swing.event.ChangeListener;


/**
 * @see <a href="http://keithp.com/~keithp/porterduff/p253-porter.pdf">Porter-Duff</a>
 * @see <a href="http://www.ibm.com/developerworks/java/library/j-mer0918/">Zukowski</a>
 * @author John B. Matthews
 */
public class AlphaCompositeDemo extends JPanel {
    private static final Rule defaultRule = Rule.Xor;
    private static final String form = "0.00";
    private static final Border border =
            BorderFactory.createEmptyBorder(4, 16, 4, 16);
    private AlphaView view;
    private JSpinner srcSpin, dstSpin;
    private JComboBox rules;
    private final JTextArea text;

    public AlphaCompositeDemo() {
        super(true);
        this.setLayout(new BorderLayout());
        System.out.println(this.isOpaque());

        JLabel titleLabel = new JLabel("AlphaComposite");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        this.add(titleLabel, BorderLayout.NORTH);

        view = new AlphaView();
        view.setBorder(border);
        this.add(view, BorderLayout.CENTER);

        rules = new JComboBox();
        for (Rule rule : Rule.values()) {
            rules.addItem(rule);
        }
        rules.setSelectedItem(defaultRule);
        view.setRule(defaultRule);
        rules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Rule rule = (Rule) rules.getSelectedItem();
                view.setRule(rule);
                text.setText(rule.getDescription());
            }
        });

        srcSpin = new JSpinner();
        srcSpin.setModel(new SpinnerNumberModel(1.0, 0, 1.0, 0.1));
        srcSpin.setEditor(new JSpinner.NumberEditor(srcSpin, form));
        srcSpin.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Number n = (Number) srcSpin.getValue();
                view.setSrcAlpha(n.floatValue());
            }
        });

        dstSpin = new JSpinner();
        dstSpin.setModel(new SpinnerNumberModel(1.0, 0, 1.0, 0.1));
        dstSpin.setEditor(new JSpinner.NumberEditor(dstSpin, form));
        dstSpin.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Number n = (Number) dstSpin.getValue();
                view.setDstAlpha(n.floatValue());
            }
        });

        JRadioButton hexButton = new JRadioButton("Hex");
        hexButton.setMnemonic(KeyEvent.VK_H);
        hexButton.setSelected(true);
        hexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.setRadix(16);
            }
        });

        JRadioButton decButton = new JRadioButton("Dec");
        decButton.setMnemonic(KeyEvent.VK_D);
        decButton.setSelected(false);
        decButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view.setRadix(10);
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(hexButton);
        group.add(decButton);

        JButton srcColor = new JButton("SrcColor");
        srcColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(
                        AlphaCompositeDemo.this,
                        "Choose Src Color", view.getSrcColor());
                if (newColor != null) {
                    view.setSrcColor(newColor);
                }
            }
        });

        JButton dstColor = new JButton("DstColor");
        dstColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(
                        AlphaCompositeDemo.this,
                        "Choose Src Color", view.getDstColor());
                if (newColor != null) {
                    view.setDstColor(newColor);
                }
            }
        });

        text = new JTextArea(3, 36);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(border);
        text.setText(defaultRule.getDescription());

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Src \u03b1:", JLabel.RIGHT));
        controlPanel.add(srcSpin);
        controlPanel.add(new JLabel("Rule:", JLabel.RIGHT));
        controlPanel.add(rules);
        controlPanel.add(new JLabel("Dst \u03b1:", JLabel.RIGHT));
        controlPanel.add(dstSpin);

        JPanel colorPanel = new JPanel();
        colorPanel.add(srcColor);
        colorPanel.add(hexButton);
        colorPanel.add(decButton);
        colorPanel.add(dstColor);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanel.add(controlPanel);
        southPanel.add(colorPanel);
        southPanel.add(text);

        this.add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String arg[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new AlphaCompositeDemo());
                f.pack();
                f.setVisible(true);
            }
        });
    }
}

enum Rule {
    Clear   (AlphaComposite.CLEAR),
    Dst     (AlphaComposite.DST),
    DstAtop (AlphaComposite.DST_ATOP),
    DstIn   (AlphaComposite.DST_IN),
    DstOut  (AlphaComposite.DST_OUT),
    DstOver (AlphaComposite.DST_OVER),
    Src     (AlphaComposite.SRC),
    SrcAtop (AlphaComposite.SRC_ATOP),
    SrcIn   (AlphaComposite.SRC_IN),
    SrcOut  (AlphaComposite.SRC_OUT),
    SrcOver (AlphaComposite.SRC_OVER),
    Xor     (AlphaComposite.XOR);

    private static final Properties glossary = new Properties();
    private final int code;

    private Rule(int code) { this.code = code; }

    public int getCode() { return this.code; }

    public String getDescription() {
        return glossary.getProperty(this.name(), this.name());
    }

    static {
        String name = "src/rule.properties";
        ClassLoader loader = Rule.class.getClassLoader();
        InputStream in = loader.getResourceAsStream(name);
        if (in != null) try {
            glossary.load(in);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}

class AlphaView extends JLabel implements Icon {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 250;
    private float srcAlpha = 1f;
    private float dstAlpha = 1f;
    private Rule rule = Rule.Xor;
    private BufferedImage src, dst;
    private Graphics2D srcG, dstG;
    private Color srcColor = Color.blue;
    private Color dstColor = Color.red;
    private Ellipse2D.Double srcE = new Ellipse2D.Double();
    private Ellipse2D.Double dstE = new Ellipse2D.Double();
    private Color hiliteColor = new Color(255, 255, 168);
    private int[] ia = new int[4];
    private int radix = 16;

    public AlphaView() {
        this.setIcon(this);
        this.setHorizontalAlignment(AlphaView.CENTER);
        int w = 2 * WIDTH / 3;
        int h = 2 * HEIGHT / 3;
        srcE.setFrame(0, h / 4, w, h);
        dstE.setFrame(w / 2, h / 4, w, h);
        src = new BufferedImage(
                WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        dst = new BufferedImage(
                WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (srcG == null) {
            srcG = src.createGraphics();
            srcG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
        if (dstG == null) {
            dstG = dst.createGraphics();
            dstG.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        srcG.setComposite(AlphaComposite.Clear);
        srcG.fillRect(0, 0, WIDTH, HEIGHT);
        srcG.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC, srcAlpha));
        srcG.setPaint(srcColor);
        srcG.drawString("Src", 20, 20);
        srcG.fill(srcE);

        dstG.setComposite(AlphaComposite.Clear);
        dstG.fillRect(0, 0, WIDTH, HEIGHT);
        dstG.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC, dstAlpha));
        dstG.setPaint(dstColor);
        dstG.drawString("Dst", WIDTH - 40, 20);
        dstG.fill(dstE);

        dstG.setComposite(AlphaComposite.getInstance(rule.getCode()));
        dstG.drawImage(src, 0, 0, null);
        g.drawImage(dst, x, y, this);

        //g.drawLine(x + WIDTH / 2, y, x + WIDTH / 2, y + HEIGHT);
        //g.drawLine(x, y + HEIGHT / 2, x + WIDTH, y + HEIGHT / 2);
        dst.getRaster().getPixel(WIDTH / 2, HEIGHT / 2, ia);
        String s = format(ia);
        int a = g.getFontMetrics().getAscent();
        int w = g.getFontMetrics().stringWidth(s);
        int h = g.getFontMetrics().getHeight();
        int bx = x + WIDTH / 2 - w / 2;
        int by = y + HEIGHT / 2 + a / 2;
        g.setColor(hiliteColor);
        g.fillRect(bx - 2, by - a, w + 3, h + 1);
        g.setColor(Color.gray);
        g.drawRect(bx - 2, by - a - 1, w + 3, h + 1);
        g.setColor(Color.black);
        g.drawString(s, bx, by);
    }

    private String format(int[] ia) {
        StringBuilder sb = new StringBuilder();
        int length = ia.length;
        for (int i = 0 ; i < length; i++) {
            sb.append(Integer.toString(ia[i], radix).toUpperCase());
            if (i < length - 1) sb.append(',');
        }
        return sb.toString();
    }

    public void setSrcAlpha(float alpha) {
        this.srcAlpha =  alpha;
        this.repaint();
    }

    public void setDstAlpha(float alpha) {
        this.dstAlpha =  alpha;
        this.repaint();
    }

    public void setRule(Rule rule) {
        this.rule =  rule;
        this.repaint();
    }

    public void setRadix(int radix) {
        this.radix = radix;
        this.repaint();
    }

    public Color getSrcColor() { return srcColor; }

    public void setSrcColor(Color srcColor) {
        this.srcColor = srcColor;
        this.repaint();
    }

    public Color getDstColor() { return dstColor; }

    public void setDstColor(Color dstColor) {
        this.dstColor = dstColor;
        this.repaint();
    }

    public int getIconWidth() { return WIDTH; }

    public int getIconHeight() { return HEIGHT; }
}