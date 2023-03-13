import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        int chunkSize = 1024 * 10;
        File somefile = new File("C:\\Users\\Shteryu\\Documents\\GitHub\\Java_Advance\\Error Handling\\src\\files\\FileZilla.xml");
        File path = new File("C:\\Users\\Shteryu\\Documents\\GitHub\\Java_Advance\\Error Handling\\src\\files");
        File mergedFilePath = new File("C:\\Users\\Shteryu\\Documents\\GitHub\\Java_Advance\\Error Handling\\src\\files\\Merged.xml");
        File[] result;
        
        
        FileSplitter.splitFileInByteChunks(somefile, chunkSize);

        result = FileSplitter.getFileList(path, "FileZilla_");

        FileSplitter.mergeChunksFiles(result, mergedFilePath);
    }
}
