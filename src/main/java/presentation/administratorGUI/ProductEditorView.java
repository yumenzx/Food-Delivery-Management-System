package presentation.administratorGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * interfata grafica pentru editorul de produse
 */
class ProductEditorView extends JFrame {

    private final JTextField textFieldTitle;
    private final JTextField textFieldRating;
    private final JTextField textFieldCalories;
    private final JTextField textFieldProteins;
    private final JTextField textFieldFats;
    private final JTextField textFieldSodium;
    private final JTextField textFieldPrice;

    private final JButton btn;

    /**
     *
     * @param flag daca flag=true => interfata va avea butonul de modificare, altfel cel de adaugare
     */
    public ProductEditorView(boolean flag){
        this.setTitle("Manager");
        this.setBounds(100, 100, 271, 368);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Manage Product");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(62, 20, 169, 23);
        this.getContentPane().add(lblNewLabel);

        JLabel lblTitle = new JLabel("Title");
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTitle.setBounds(10, 53, 67, 23);
        this.getContentPane().add(lblTitle);

        JLabel lblRating = new JLabel("Rating");
        lblRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRating.setBounds(10, 86, 67, 23);
        this.getContentPane().add(lblRating);

        JLabel lblCalories = new JLabel("Calories");
        lblCalories.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCalories.setBounds(10, 119, 67, 23);
        this.getContentPane().add(lblCalories);

        JLabel lblProteins = new JLabel("Proteins");
        lblProteins.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProteins.setBounds(10, 152, 67, 23);
        this.getContentPane().add(lblProteins);

        JLabel lblFats = new JLabel("Fats");
        lblFats.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblFats.setBounds(10, 182, 67, 23);
        this.getContentPane().add(lblFats);

        JLabel lblSodium = new JLabel("Sodium");
        lblSodium.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSodium.setBounds(10, 215, 67, 23);
        this.getContentPane().add(lblSodium);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrice.setBounds(10, 248, 67, 23);
        this.getContentPane().add(lblPrice);

        textFieldTitle = new JTextField();
        textFieldTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldTitle.setBounds(84, 53, 134, 23);
        this.getContentPane().add(textFieldTitle);
        textFieldTitle.setColumns(10);

        textFieldRating = new JTextField();
        textFieldRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldRating.setColumns(10);
        textFieldRating.setBounds(84, 86, 134, 23);
        this.getContentPane().add(textFieldRating);

        textFieldCalories = new JTextField();
        textFieldCalories.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldCalories.setColumns(10);
        textFieldCalories.setBounds(84, 119, 134, 23);
        this.getContentPane().add(textFieldCalories);

        textFieldProteins = new JTextField();
        textFieldProteins.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldProteins.setColumns(10);
        textFieldProteins.setBounds(84, 152, 134, 23);
        this.getContentPane().add(textFieldProteins);

        textFieldFats = new JTextField();
        textFieldFats.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldFats.setColumns(10);
        textFieldFats.setBounds(84, 182, 134, 23);
        this.getContentPane().add(textFieldFats);

        textFieldSodium = new JTextField();
        textFieldSodium.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldSodium.setColumns(10);
        textFieldSodium.setBounds(84, 215, 134, 23);
        this.getContentPane().add(textFieldSodium);

        textFieldPrice = new JTextField();
        textFieldPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textFieldPrice.setColumns(10);
        textFieldPrice.setBounds(84, 248, 134, 23);
        this.getContentPane().add(textFieldPrice);

        if(flag){
            btn = new JButton("Modify");
            btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            btn.setBounds(84, 294, 85, 23);
        }
        else{
            btn = new JButton("Add");
            btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
            btn.setBounds(84, 291, 85, 23);
        }
        this.getContentPane().add(btn);

        this.setVisible(true);
    }

    public void addListeners(ActionListener btnListener){
        btn.addActionListener(btnListener);
    }

    public String getTextFieldTitle() {
        return textFieldTitle.getText();
    }

    public void setTextFieldTitle(String textFieldTitle) {
        this.textFieldTitle.setText(textFieldTitle);
    }

    public String getTextFieldRating() {
        return textFieldRating.getText();
    }

    public void setTextFieldRating(String textFieldRating) {
        this.textFieldRating.setText(textFieldRating);
    }

    public String getTextFieldCalories() {
        return textFieldCalories.getText();
    }

    public void setTextFieldCalories(String textFieldCalories) {
        this.textFieldCalories.setText(textFieldCalories);
    }

    public String getTextFieldProteins() {
        return textFieldProteins.getText();
    }

    public void setTextFieldProteins(String textFieldProteins) {
        this.textFieldProteins.setText(textFieldProteins);
    }

    public String getTextFieldFats() {
        return textFieldFats.getText();
    }

    public void setTextFieldFats(String textFieldFats) {
        this.textFieldFats.setText(textFieldFats);
    }

    public String getTextFieldSodium() {
        return textFieldSodium.getText();
    }

    public void setTextFieldSodium(String textFieldSodium) {
        this.textFieldSodium.setText(textFieldSodium);
    }

    public String getTextFieldPrice() {
        return textFieldPrice.getText();
    }

    public void setTextFieldPrice(String textFieldPrice) {
        this.textFieldPrice.setText(textFieldPrice);
    }

}
