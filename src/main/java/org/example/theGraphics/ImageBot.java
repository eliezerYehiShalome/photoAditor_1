//package org.example.theGraphics;
//
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//import org.telegram.telegrambots.meta.api.methods.GetFile;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//
//import static org.example.theGraphics.ImagePanel.newIMG;
//
//public class ImageBot extends TelegramLongPollingBot {
//
//    BufferedImage IMG;
//
//    @Override
//    public void onUpdateReceived(Update update) {
//
//        if (update.hasMessage() && update.getMessage().hasPhoto()) {
//            // קבלת התמונה מהמשתמש
//            SendPhoto sendPhoto = new SendPhoto();
//            sendPhoto.setChatId(update.getMessage().getChatId());
//
//            // נסיון לטעון את התמונה מהטלגרם
//            try {
//                InputFile photo = new InputFile(update.getMessage().getPhoto().get(0).getFileId());
//                sendPhoto.setPhoto(photo);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            try {
//                InputStream photoInputStream = downloadPhotoByFileId(update.getMessage().getPhoto().get(0).getFileId());
//                 IMG = ImageIO.read(photoInputStream);
//                // השתמש ב- newIMG
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            SendMessage sendMessage=new SendMessage();
//            sendMessage.setChatId(update.getMessage().getChatId());
//            sendMessage.setText("tank you||");
//
//            newIMG=IMG;
//
//
//
//            try {
//                execute(sendMessage);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//
//            try {
//                execute(sendPhoto);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public InputStream downloadPhotoByFileId(String fileId) {
//        try {
//            GetFile getFileMethod = new GetFile();
//            getFileMethod.setFileId(fileId);
//            org.telegram.telegrambots.meta.api.objects.File file = execute(getFileMethod);
//            String filePath = file.getFilePath();
//
//            // Construct the URL to download the photo
//            String fileUrl = getBotToken() + "/file/bot" + filePath;
//
//            // Download the photo and return it as an InputFile
//            InputStream inputStream = new URL(fileUrl).openStream();
//            return new InputFile(inputStream, "photo.jpg").getNewMediaStream();
//        } catch (IOException | TelegramApiException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    @Override
//    public String getBotUsername() {
//        return "imageAditorEliYs_bot";
//    }
//
//    @Override
//    public String getBotToken() {
//        return "6461947540:AAGEOKeSGjXg6wEPwvUTBLZ37WJtLGQiY6c";
//    }
//
//}