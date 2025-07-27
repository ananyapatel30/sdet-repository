import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageCompressorApp extends JFrame {

    private BufferedImage compressedImage;
    private String originalFileName;

    public ImageCompressorApp() {
        // Create the main application window
        setTitle("Image Compressor");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JButton uploadButton = new JButton("Upload Image");
        JButton downloadButton = new JButton("Download Compressed Image");
        downloadButton.setEnabled(false); // Disabled until an image is uploaded
        JLabel statusLabel = new JLabel("Please upload an image to compress.");

        // Add action listeners for buttons
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadImage(statusLabel, downloadButton);
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (compressedImage != null) {
                    downloadCompressedImage(statusLabel);
                }
            }
        });

        // Set up the layout
        JPanel panel = new JPanel();
        panel.add(uploadButton);
        panel.add(downloadButton);
        panel.add(statusLabel);

        add(panel);
    }

    // Method to upload the image
    private void uploadImage(JLabel statusLabel, JButton downloadButton) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage inputImage = ImageIO.read(selectedFile);
                if (inputImage != null) {
                    // Compress the image and store it in memory
                    compressedImage = compressImage(inputImage, 0.5f); // 50% quality
                    // Store the original image name without extension
                    originalFileName = getFileNameWithoutExtension(selectedFile);
                    statusLabel.setText("Image uploaded and compressed. Ready to download.");
                    downloadButton.setEnabled(true); // Enable download button
                } else {
                    statusLabel.setText("Invalid image file.");
                }
            } catch (IOException ex) {
                statusLabel.setText("Error reading the image: " + ex.getMessage());
            }
        } else {
            statusLabel.setText("Image upload canceled.");
        }
    }

    // Method to allow user to download the compressed image
    private void downloadCompressedImage(JLabel statusLabel) {
        JFileChooser saveChooser = new JFileChooser();
        saveChooser.setDialogTitle("Save Compressed Image");
        saveChooser.setDialogType(JFileChooser.SAVE_DIALOG);

        // Prepopulate the filename with the original image name + _compressed.jpg
        saveChooser.setSelectedFile(new File(originalFileName + "_compressed.jpg"));

        int saveResult = saveChooser.showSaveDialog(this);
        if (saveResult == JFileChooser.APPROVE_OPTION) {
            File destinationFile = saveChooser.getSelectedFile();
            try {
                // Save the compressed image to the selected location
                ImageIO.write(compressedImage, "jpg", destinationFile);
                statusLabel.setText("Compressed image saved as: " + destinationFile.getAbsolutePath());
            } catch (IOException ex) {
                statusLabel.setText("Error saving the compressed image: " + ex.getMessage());
            }
        } else {
            statusLabel.setText("Download canceled.");
        }
    }

    // Method to compress the image (returns the compressed image in memory)
    public static BufferedImage compressImage(BufferedImage originalImage, float quality) {
        // Resize the image (optional, change to suit your needs)
        int targetWidth = originalImage.getWidth() / 2;
        int targetHeight = originalImage.getHeight() / 2;
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, originalImage.getType());

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2d.dispose();

        return resizedImage; // Return the compressed image in memory
    }

    // Helper method to extract file name without extension
    private static String getFileNameWithoutExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

    public static void main(String[] args) {
        // Run the ImageCompressorApp
        ImageCompressorApp app = new ImageCompressorApp();
        app.setVisible(true);
    }
}
