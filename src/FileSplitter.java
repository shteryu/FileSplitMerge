import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileSplitter {

    public static void splitFileInByteChunks(File file, Integer chunkSize) throws IOException {
        int currentChunkNo = 1;

        try (FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis)) {

            byte[] buffer = new byte[chunkSize];
            int bytesreadForChunk;
            String filename = file.toString().substring(0, file.toString().lastIndexOf('.'));
            String extension = file.toString().substring( file.toString().indexOf('.'));
            System.out.println(extension);
            while ((bytesreadForChunk = bis.readNBytes(buffer, 0, chunkSize)) > 0) {
                String chunkFileFilename =
                        String.format("%s_%d%s", filename, currentChunkNo++, extension);

                try (FileOutputStream fos = new FileOutputStream(chunkFileFilename)) {
                    fos.write(buffer);
                }
            }

        }
    }

    public static void mergeChunksFiles(File[] files, File mergedFile){
        FileWriter fstream = null;
        BufferedWriter out = null;

        try{
            fstream = new FileWriter(mergedFile, true);
            out = new BufferedWriter(fstream);
        } catch (IOException e){
            e.printStackTrace();
        }

        for (File f : files){
            FileInputStream fis;
            try {
                fis = new FileInputStream(f);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));

                String aLine;
                while ((aLine = in.readLine()) != null) {
                    out.write(aLine);
                    out.newLine();
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File[] getFileList(File dir, String fileName) { 

        File[] fileList = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.contains(fileName);
            }
        });
        return fileList;
    }
}
