package de.litexo.utils.test.extensions.folder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class TemporaryFolder {

    private Path root;

    /**
     * Deletes all tmp files.
     */
    public void after() {

        delete();
    }

    protected Path getRoot() throws IOException {

        if (this.root == null) {
            this.root = createTemporaryFolder(null);
        }
        return this.root;
    }

    protected void delete() {

        if (this.root != null) {
            recursiveDelete(this.root);
            this.root = null;
        }
    }


    public Path newFile(String fileName) throws IOException {

        File file = new File(getRoot().toFile(), fileName);
        if (!file.createNewFile()) {
            throw new IOException(String.format("failed to create file %s in folder %s", fileName, getRoot()));
        }
        return file.toPath();
    }


    public Path newFile() throws IOException {

        return File.createTempFile("junit", null, getRoot().toFile()).toPath();
    }

    public File newFolder(String folderName) throws IOException {

        return Files.createDirectories(this.getRoot().resolve(folderName)).toFile();
    }

    protected Path newRootFolder() throws IOException {
        return createTemporaryFolder(getRoot());
    }

    private Path createTemporaryFolder(Path base) throws IOException {
        File baseFile = base==null?null: base.toFile();
        File createdFolder = File.createTempFile("junit", "",baseFile);
        createdFolder.delete();
        createdFolder.mkdir();
        return createdFolder.toPath();
    }

    private void recursiveDelete(Path file) {

        File[] files = file.toFile().listFiles();
        if (files != null) {
            for (File each : files) {
                recursiveDelete(each.toPath());
            }
        }

        if (!file.toFile().delete()) {
            file.toFile().deleteOnExit();
        }
    }

}
