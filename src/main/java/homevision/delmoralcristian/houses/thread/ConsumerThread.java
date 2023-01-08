package homevision.delmoralcristian.houses.thread;

import homevision.delmoralcristian.houses.enums.CommonMessage;
import homevision.delmoralcristian.houses.exceptions.InternalServerErrorException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class ConsumerThread implements Runnable {

    private String fileName;
    private String link;
    private File out;

    @Override
    public void run() {
        try {
            var url = new URL(this.link);
            var http = (HttpURLConnection) url.openConnection();
            var fileSize = (double) http.getContentLengthLong();
            var in = new BufferedInputStream(http.getInputStream());
            var fos = new FileOutputStream(this.out);
            var bout = new BufferedOutputStream(fos, 1024);
            var buffer = new byte[1024];
            var downloaded = 0.00;
            int read;
            double percentDownloaded;
            while ((read = in.read(buffer, 0, 1024)) >= 0) {
                bout.write(buffer, 0, read);
                downloaded += read;
                percentDownloaded = (downloaded * 100) / fileSize;
                String percent = String.format("%.4f", percentDownloaded);
                var logMessage = new StringBuilder("Downloaded ")
                        .append(this.fileName)
                        .append(percent)
                        .append(" % of a file.");
                log.info(logMessage.toString());
            }
            bout.close();
            in.close();
            log.info("Download complete.");
        } catch (Exception e) {
            throw new InternalServerErrorException(CommonMessage.PHOTO_DOWNLOAD_ERROR.getMessage());
        }
    }
}
