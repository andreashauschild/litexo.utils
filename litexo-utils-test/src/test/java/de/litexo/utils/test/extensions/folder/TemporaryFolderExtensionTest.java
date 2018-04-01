package de.litexo.utils.test.extensions.folder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Andreas Hauschild
 */
@ExtendWith(TemporaryFolderExtension.class)
public class TemporaryFolderExtensionTest {

    @TempFolder
    private TemporaryFolder folder;

    @DisplayName("@TempFolder - Test field injection")
    @Test
    public void test_001(){
        assertNotNull(this.folder);
    }

    @DisplayName("@TempFolder - Test parameter injection")
    @Test
    public void test_002(@TempFolder TemporaryFolder param){
        assertNotNull(param);
    }

    @DisplayName("Test new folder will create a folder in root directory")
    @Test
    public void test_003() throws IOException {
        assertEquals(folder.getRoot().resolve("XXX").toAbsolutePath().toString(),folder.newFolder("XXX").toPath().toAbsolutePath().toString());
    }
}