package com.hiarcdb.client.integrationTests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.model.AccessLevel;
import com.hiarcdb.client.model.AddFileToCollectionRequest;
import com.hiarcdb.client.model.AddGroupToCollectionRequest;
import com.hiarcdb.client.model.AddUserToCollectionRequest;
import com.hiarcdb.client.model.AddUserToFileRequest;
import com.hiarcdb.client.model.AddVersionToFileRequest;
import com.hiarcdb.client.model.AllowedFilesRequest;
import com.hiarcdb.client.model.Collection;
import com.hiarcdb.client.model.CopyFileRequest;
import com.hiarcdb.client.model.CreateDirectUploadUrlRequest;
import com.hiarcdb.client.model.CreateFileRequest;
import com.hiarcdb.client.model.FileDirectDownload;
import com.hiarcdb.client.model.FileDirectUpload;
import com.hiarcdb.client.model.FileVersion;
import com.hiarcdb.client.model.Group;
import com.hiarcdb.client.model.HiarcFile;
import com.hiarcdb.client.model.UpdateFileRequest;
import com.hiarcdb.client.model.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalUnit;

public class FileTest {
    private final HiarcTestUtil util = new HiarcTestUtil();

    @Before
    public void setup() {
        try {
            util.admin.resetDB();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * File CRUD
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void fileCRUDTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        HiarcFile gf = util.files.getFile(f1.getKey(), null);
        Assert.assertEquals(f1, gf);

        String newName = "New Name.txt";
        String newDescription = "New description";
        UpdateFileRequest ufr = new UpdateFileRequest();
        ufr.setName(newName);
        ufr.setDescription(newDescription);

        HiarcFile uf = util.files.updateFile(f1.getKey(), ufr, null);
        Assert.assertEquals(uf.getName(), newName);
        Assert.assertEquals(uf.getDescription(), newDescription);
        Assert.assertTrue(uf.getModifiedAt().isAfter(uf.getCreatedAt()));

        Assert.assertThrows(ApiException.class, () -> {
            ufr.setKey("new key");
            ufr.setName(newName);
            ufr.setDescription(newDescription);

            util.files.updateFile(f1.getKey(), ufr, null);
        });

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * File Versions
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void fileVersionsTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        Assert.assertEquals(f1.getVersionCount(), 1);

        File nf = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
        AddVersionToFileRequest avtfr = new AddVersionToFileRequest();
        avtfr.setKey(f1.getKey());
        HiarcFile nvf1 = util.files.addVersion(f1.getKey(), avtfr, nf, null);
        Assert.assertEquals(nvf1.getVersionCount(), 2);
        Assert.assertTrue(nvf1.getModifiedAt().isAfter(f1.getModifiedAt()));

        File nf2 = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
        AddVersionToFileRequest avtfr2 = new AddVersionToFileRequest();
        avtfr2.setKey(f1.getKey());
        HiarcFile nvf2 = util.files.addVersion(f1.getKey(), avtfr, nf2, null);
        Assert.assertEquals(nvf2.getVersionCount(), 3);
        Assert.assertTrue(nvf2.getModifiedAt().isAfter(nvf1.getModifiedAt()));

        List<FileVersion> fv = util.files.getVersions(f1.getKey(), null);
        Assert.assertEquals(fv.size(), 3);
        Assert.assertTrue(fv.get(0).getCreatedAt().isBefore(fv.get(2).getCreatedAt()));

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * File Versions Created By
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void fileVersionsCreatedByTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        Assert.assertEquals(f1.getVersionCount(), 1);

        User u1 = util.users.createUser(util.createUser());
        AddUserToFileRequest autfr = new AddUserToFileRequest();
        autfr.setAccessLevel(AccessLevel.READ_WRITE);
        autfr.setUserKey(u1.getKey());
        util.files.addUserToFile(f1.getKey(), autfr, null);

        File nf = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
        AddVersionToFileRequest avtfr = new AddVersionToFileRequest();
        avtfr.setKey(f1.getKey());
        HiarcFile nvf1 = util.files.addVersion(f1.getKey(), avtfr, nf, u1.getKey());
        Assert.assertEquals(nvf1.getVersionCount(), 2);
        Assert.assertTrue(nvf1.getModifiedAt().isAfter(f1.getModifiedAt()));

        List<FileVersion> fv = util.files.getVersions(f1.getKey(), null);
        Assert.assertEquals(fv.size(), 2);
        Assert.assertTrue(fv.get(0).getCreatedAt().isBefore(fv.get(1).getCreatedAt()));
        Assert.assertTrue(fv.get(0).getCreatedBy().equals(util.ADMIN_NAME));
        Assert.assertTrue(fv.get(1).getCreatedBy().equals(u1.getKey()));

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * File Versions Respect Access Levels
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void fileVersionsRespectAccessLevels() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        Assert.assertEquals(f1.getVersionCount(), 1);

        User u1 = util.users.createUser(util.createUser());
        AddUserToFileRequest autfr = new AddUserToFileRequest();
        autfr.setAccessLevel(AccessLevel.READ_ONLY);
        autfr.setUserKey(u1.getKey());
        util.files.addUserToFile(f1.getKey(), autfr, null);
        Assert.assertThrows(ApiException.class, () -> {
            File nf = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
            AddVersionToFileRequest avtfr = new AddVersionToFileRequest();
            avtfr.setKey(f1.getKey());
            util.files.addVersion(f1.getKey(), avtfr, nf, u1.getKey());
        });

        User u2 = util.users.createUser(util.createUser());
        AddUserToFileRequest autfr2 = new AddUserToFileRequest();
        autfr2.setAccessLevel(AccessLevel.UPLOAD_ONLY);
        autfr2.setUserKey(u2.getKey());
        util.files.addUserToFile(f1.getKey(), autfr2, null);

        File nf = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
        AddVersionToFileRequest avtfr = new AddVersionToFileRequest();
        avtfr.setKey(f1.getKey());
        util.files.addVersion(f1.getKey(), avtfr, nf, u2.getKey());

        User u3 = util.users.createUser(util.createUser());
        AddUserToFileRequest autfr3 = new AddUserToFileRequest();
        autfr3.setAccessLevel(AccessLevel.READ_WRITE);
        autfr3.setUserKey(u3.getKey());
        util.files.addUserToFile(f1.getKey(), autfr3, null);

        File nf3 = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
        AddVersionToFileRequest avtfr3 = new AddVersionToFileRequest();
        avtfr3.setKey(f1.getKey());
        util.files.addVersion(f1.getKey(), avtfr3, nf3, u3.getKey());

        User u4 = util.users.createUser(util.createUser());
        AddUserToFileRequest autfr4 = new AddUserToFileRequest();
        autfr4.setAccessLevel(AccessLevel.CO_OWNER);
        autfr4.setUserKey(u4.getKey());
        util.files.addUserToFile(f1.getKey(), autfr4, null);

        File nf4 = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
        AddVersionToFileRequest avtfr4 = new AddVersionToFileRequest();
        avtfr4.setKey(f1.getKey());
        util.files.addVersion(f1.getKey(), avtfr4, nf4, u4.getKey());

        List<FileVersion> fv = util.files.getVersions(f1.getKey(), null);
        Assert.assertEquals(fv.size(), 4);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * File Version Stays in Last Storage Service
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void fileVersionStaysInLastStorageServiceTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        CreateFileRequest cfr = util.createFile();
        cfr.setStorageService(util.AWS_EAST_STORAGE);
        HiarcFile f1 = util.files.createFile(cfr, f, null);

        AddVersionToFileRequest avtfr = new AddVersionToFileRequest();
        avtfr.setKey(f1.getKey());

        util.files.addVersion(f1.getKey(), avtfr, new File(util.NEW_VERSION_ABS_TEST_FILE_PATH), null);
        avtfr.setStorageService(util.AZURE_STORAGE_1);
        util.files.addVersion(f1.getKey(), avtfr, new File(util.NEW_VERSION_ABS_TEST_FILE_PATH), null);
        util.files.addVersion(f1.getKey(), avtfr, new File(util.NEW_VERSION_ABS_TEST_FILE_PATH), null);

        List<FileVersion> fv = util.files.getVersions(f1.getKey(), null);
        Assert.assertEquals(fv.size(), 4);
        Assert.assertTrue(fv.get(0).getStorageService().equals(util.AWS_EAST_STORAGE));
        Assert.assertTrue(fv.get(1).getStorageService().equals(util.AWS_EAST_STORAGE));
        Assert.assertTrue(fv.get(2).getStorageService().equals(util.AZURE_STORAGE_1));
        Assert.assertTrue(fv.get(3).getStorageService().equals(util.AZURE_STORAGE_1));

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Get Allowed Files
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getAllowedFilesTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f2 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f3 = util.files.createFile(util.createFile(), f, null);
        HiarcFile f4 = util.files.createFile(util.createFile(), f, null);

        User u1 = util.users.createUser(util.createUser());
        AddUserToFileRequest autfr = new AddUserToFileRequest();
        autfr.setAccessLevel(AccessLevel.READ_ONLY);
        autfr.setUserKey(u1.getKey());
        util.files.addUserToFile(f1.getKey(), autfr, null);

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f2.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null);

        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);
        util.collections.addChildToCollection(c2.getKey(), c3.getKey(), null);

        AddFileToCollectionRequest aftcr2 = new AddFileToCollectionRequest();
        aftcr2.setFileKey(f3.getKey());
        util.collections.addFileToCollection(c3.getKey(), aftcr2, null);

        AddUserToCollectionRequest autcr2 = new AddUserToCollectionRequest();
        autcr2.setAccessLevel(AccessLevel.READ_ONLY);
        autcr2.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c2.getKey(), autcr2, null);

        AllowedFilesRequest afr = new AllowedFilesRequest();
        afr.setKeys(Arrays.asList(f1.getKey(), f2.getKey(), f3.getKey(), f4.getKey()));
        List<String> af = util.files.filterAllowedFiles(afr, u1.getKey());
        assertThat(af, hasItem(f1.getKey()));
        assertThat(af, hasItem(f2.getKey()));
        assertThat(af, hasItem(f3.getKey()));
        assertThat(af, not(hasItem(f4.getKey())));

        for (String k : Arrays.asList(f1.getKey(), f2.getKey(), f3.getKey(), f4.getKey())) {
            util.files.deleteFile(k, null);
            Assert.assertThrows(ApiException.class, () -> {
                util.files.getFile(k, null);
            });
        }
    }

    /**
     * Download Files
     *
     * 
     *
     * @throws ApiException             if the Api call fails
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    @Test
    public void downloadFileTest() throws ApiException, NoSuchAlgorithmException, IOException {
        for (String s : util.All_STORAGE_SERVICES) {
            CreateFileRequest cfr = util.createFile();
            cfr.setStorageService(s);
            File upload = new File(util.ABS_TEST_FILE_PATH);
            HiarcFile f1 = util.files.createFile(cfr, upload, null);

            this.util.files.downloadFile(f1.getKey(), util.ABS_TEST_DOWNLOAD_FILE_PATH, null);
            File download = new File(util.ABS_TEST_DOWNLOAD_FILE_PATH);
            String hash1 = util.getFileChecksum(upload);
            String hash2 = util.getFileChecksum(download);
            Assert.assertEquals(hash1, hash2);

            util.files.deleteFile(f1.getKey(), null);
            Assert.assertThrows(ApiException.class, () -> {
                util.files.getFile(f1.getKey(), null);
            });
        }
    }

    /**
     * Direct Download Url
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void directDownloadUrlTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);
        FileDirectDownload fdd = util.files.getDirectDownloadUrl(f1.getKey(), null, null);

        Assert.assertTrue(fdd.getDirectDownloadUrl().startsWith("https"));
        Assert.assertTrue(fdd.getExpiresAt().isAfter(OffsetDateTime.now()));
        Assert.assertTrue(fdd.getExpiresAt()
                .isBefore(OffsetDateTime.now().plus(util.DEFAULT_EXPIRES_IN_SECONDS, ChronoUnit.SECONDS)));

        int specifiedExpiration = 60;
        FileDirectDownload fdd2 = util.files.getDirectDownloadUrl(f1.getKey(), null, specifiedExpiration);
        Assert.assertTrue(
                fdd2.getExpiresAt().isAfter(OffsetDateTime.now().plus(specifiedExpiration - 5, ChronoUnit.SECONDS)));
        Assert.assertTrue(
                fdd2.getExpiresAt().isBefore(OffsetDateTime.now().plus(specifiedExpiration, ChronoUnit.SECONDS)));
        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Direct Upload Url
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void directUploadUrlTest() throws ApiException {
        FileDirectUpload fdu = util.files.createDirectUploadUrl(null, null, null);

        Assert.assertTrue(fdu.getDirectUploadUrl().startsWith("https"));
    }

    /**
     * Download New Version of Files
     *
     * 
     *
     * @throws ApiException             if the Api call fails
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    @Test
    public void downloadNewVersionOfFileTest() throws ApiException, NoSuchAlgorithmException, IOException {
        CreateFileRequest cfr = util.createFile();
        File upload = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(cfr, upload, null);

        File nf = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
        AddVersionToFileRequest avtfr = new AddVersionToFileRequest();
        avtfr.setKey(f1.getKey());
        util.files.addVersion(f1.getKey(), avtfr, nf, null);

        this.util.files.downloadFile(f1.getKey(), util.ABS_TEST_DOWNLOAD_FILE_PATH, null);
        File download = new File(util.ABS_TEST_DOWNLOAD_FILE_PATH);
        String hash1 = util.getFileChecksum(nf);
        String hash2 = util.getFileChecksum(download);
        Assert.assertEquals(hash1, hash2);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Copy Files to and from AWS
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void copyFileAwsTest() throws ApiException {
        CreateFileRequest cfr = util.createFile();
        cfr.setStorageService(util.AWS_EAST_STORAGE);
        File upload = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile source = util.files.createFile(cfr, upload, null);

        CopyFileRequest copfr = new CopyFileRequest();
        copfr.setKey(util.generateKey("file"));
        copfr.setStorageService(util.AWS_EAST_STORAGE);
        HiarcFile copy = util.files.copyFile(source.getKey(), copfr, null);
        Assert.assertFalse(source.getKey().equals(copy.getKey()));

        CopyFileRequest copfrWest = new CopyFileRequest();
        copfrWest.setKey(util.generateKey("file"));
        copfrWest.setStorageService(util.AWS_WEST_STORAGE);
        HiarcFile awsWestCopy = util.files.copyFile(source.getKey(), copfrWest, null);
        Assert.assertFalse(source.getKey().equals(awsWestCopy.getKey()));

        CopyFileRequest copfrAzure = new CopyFileRequest();
        copfrAzure.setKey(util.generateKey("file"));
        copfrAzure.setStorageService(util.AZURE_STORAGE_1);
        HiarcFile azureCopy = util.files.copyFile(source.getKey(), copfrAzure, null);
        Assert.assertFalse(source.getKey().equals(azureCopy.getKey()));

        CopyFileRequest copfrGoogle = new CopyFileRequest();
        copfrGoogle.setKey(util.generateKey("file"));
        copfrGoogle.setStorageService(util.GOOGLE_EAST_STORAGE);
        HiarcFile googleCopy = util.files.copyFile(source.getKey(), copfrGoogle, null);
        Assert.assertFalse(source.getKey().equals(googleCopy.getKey()));

        for (String k : Arrays.asList(source.getKey(), copy.getKey(), awsWestCopy.getKey(), azureCopy.getKey(),
                googleCopy.getKey())) {
            util.files.deleteFile(k, null);
            Assert.assertThrows(ApiException.class, () -> {
                util.files.getFile(k, null);
            });
        }
    }

    /**
     * Copy Files to and from Azure
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void copyFileAzureTest() throws ApiException {
        CreateFileRequest cfr = util.createFile();
        cfr.setStorageService(util.AZURE_STORAGE_1);
        File upload = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile source = util.files.createFile(cfr, upload, null);

        CopyFileRequest copfr = new CopyFileRequest();
        copfr.setKey(util.generateKey("file"));
        copfr.setStorageService(util.AZURE_STORAGE_1);
        HiarcFile copy = util.files.copyFile(source.getKey(), copfr, null);
        Assert.assertFalse(source.getKey().equals(copy.getKey()));

        CopyFileRequest copfr2 = new CopyFileRequest();
        copfr2.setKey(util.generateKey("file"));
        copfr2.setStorageService(util.AZURE_STORAGE_2);
        HiarcFile azure2Copy = util.files.copyFile(source.getKey(), copfr2, null);
        Assert.assertFalse(source.getKey().equals(azure2Copy.getKey()));

        CopyFileRequest copfrAws = new CopyFileRequest();
        copfrAws.setKey(util.generateKey("file"));
        copfrAws.setStorageService(util.AWS_EAST_STORAGE);
        HiarcFile awsCopy = util.files.copyFile(source.getKey(), copfrAws, null);
        Assert.assertFalse(source.getKey().equals(awsCopy.getKey()));

        CopyFileRequest copfrGoogle = new CopyFileRequest();
        copfrGoogle.setKey(util.generateKey("file"));
        copfrGoogle.setStorageService(util.GOOGLE_EAST_STORAGE);
        HiarcFile googleCopy = util.files.copyFile(source.getKey(), copfrGoogle, null);
        Assert.assertFalse(source.getKey().equals(googleCopy.getKey()));

        for (String k : Arrays.asList(source.getKey(), copy.getKey(), azure2Copy.getKey(), awsCopy.getKey(),
                googleCopy.getKey())) {
            util.files.deleteFile(k, null);
            Assert.assertThrows(ApiException.class, () -> {
                util.files.getFile(k, null);
            });
        }
    }

    /**
     * Copy Files to and from Google
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void copyFileGoogleTest() throws ApiException {
        CreateFileRequest cfr = util.createFile();
        cfr.setStorageService(util.GOOGLE_EAST_STORAGE);
        File upload = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile source = util.files.createFile(cfr, upload, null);

        CopyFileRequest copfr = new CopyFileRequest();
        copfr.setKey(util.generateKey("file"));
        copfr.setStorageService(util.GOOGLE_EAST_STORAGE);
        HiarcFile copy = util.files.copyFile(source.getKey(), copfr, null);
        Assert.assertFalse(source.getKey().equals(copy.getKey()));

        CopyFileRequest copfr2 = new CopyFileRequest();
        copfr2.setKey(util.generateKey("file"));
        copfr2.setStorageService(util.GOOGLE_WEST_STORAGE);
        HiarcFile azure2Copy = util.files.copyFile(source.getKey(), copfr2, null);
        Assert.assertFalse(source.getKey().equals(azure2Copy.getKey()));

        CopyFileRequest copfrAws = new CopyFileRequest();
        copfrAws.setKey(util.generateKey("file"));
        copfrAws.setStorageService(util.AWS_EAST_STORAGE);
        HiarcFile awsCopy = util.files.copyFile(source.getKey(), copfrAws, null);
        Assert.assertFalse(source.getKey().equals(awsCopy.getKey()));

        CopyFileRequest copfrAzure = new CopyFileRequest();
        copfrAzure.setKey(util.generateKey("file"));
        copfrAzure.setStorageService(util.AZURE_STORAGE_1);
        HiarcFile azureCopy = util.files.copyFile(source.getKey(), copfrAzure, null);
        Assert.assertFalse(source.getKey().equals(azureCopy.getKey()));

        for (String k : Arrays.asList(source.getKey(), copy.getKey(), azure2Copy.getKey(), awsCopy.getKey(),
                azureCopy.getKey())) {
            util.files.deleteFile(k, null);
            Assert.assertThrows(ApiException.class, () -> {
                util.files.getFile(k, null);
            });
        }
    }

    /**
     * Complex Delete
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void complexDeleteTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);

        File nf = new File(util.NEW_VERSION_ABS_TEST_FILE_PATH);
        AddVersionToFileRequest avtfr = new AddVersionToFileRequest();
        avtfr.setKey(f1.getKey());
        util.files.addVersion(f1.getKey(), avtfr, nf, null);
        util.files.addVersion(f1.getKey(), avtfr, nf, null);

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Get File Collections
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getFileCollectionsTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);
        for (String k : Arrays.asList(c1.getKey(), c2.getKey(), c3.getKey())) {
            AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
            aftcr.setFileKey(f1.getKey());
            util.collections.addFileToCollection(k, aftcr, null);
        }

        List<Collection> collections = util.files.getCollectionsForFile(f1.getKey(), null);
        Assert.assertEquals(collections.size(), 3);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * User Can Access File in Nested Child
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userCanAccessFileInChildTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);

        User u1 = util.users.createUser(util.createUser());

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);

        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), null);
        util.collections.addChildToCollection(c2.getKey(), c3.getKey(), null);

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null); // add user to parent collection

        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c3.getKey(), aftcr, null); // add file to nest child

        HiarcFile ff = util.files.getFile(f1.getKey(), u1.getKey());
        Assert.assertEquals(f1, ff);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * User Can Access File in Parent
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userCanAccessFileInParentTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);

        User u1 = util.users.createUser(util.createUser());

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);

        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), null);
        util.collections.addChildToCollection(c2.getKey(), c3.getKey(), null);

        AddUserToCollectionRequest autcr = new AddUserToCollectionRequest();
        autcr.setAccessLevel(AccessLevel.READ_ONLY);
        autcr.setUserKey(u1.getKey());
        util.collections.addUserToCollection(c1.getKey(), autcr, null); // add user to parent collection

        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null); // add file to nest child

        HiarcFile ff = util.files.getFile(f1.getKey(), u1.getKey());
        Assert.assertEquals(f1, ff);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * User Can Access File in Nested Child based on Group Access
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userCanAccessFileInChildByGroupTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);

        User u1 = util.users.createUser(util.createUser());
        Group g1 = util.groups.createGroup(util.createGroup());
        util.groups.addUserToGroup(g1.getKey(), u1.getKey());

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);

        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), null);
        util.collections.addChildToCollection(c2.getKey(), c3.getKey(), null);

        AddGroupToCollectionRequest agtcr = new AddGroupToCollectionRequest();
        agtcr.setAccessLevel(AccessLevel.READ_ONLY);
        agtcr.setGroupKey(g1.getKey());
        util.collections.addGroupToCollection(c1.getKey(), agtcr, null); // add group to parent collection

        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c3.getKey(), aftcr, null); // add file to nest child

        HiarcFile ff = util.files.getFile(f1.getKey(), u1.getKey());
        Assert.assertEquals(f1, ff);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * User Can Access File in Parent based on Group Access
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userCanAccessFileInParentByGroupTest() throws ApiException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(), f, null);

        User u1 = util.users.createUser(util.createUser());
        Group g1 = util.groups.createGroup(util.createGroup());
        util.groups.addUserToGroup(g1.getKey(), u1.getKey());

        Collection c1 = util.collections.createCollection(util.createCollection(), null);
        Collection c2 = util.collections.createCollection(util.createCollection(), null);
        Collection c3 = util.collections.createCollection(util.createCollection(), null);

        util.collections.addChildToCollection(c1.getKey(), c2.getKey(), null);
        util.collections.addChildToCollection(c2.getKey(), c3.getKey(), null);

        AddGroupToCollectionRequest agtcr = new AddGroupToCollectionRequest();
        agtcr.setAccessLevel(AccessLevel.READ_ONLY);
        agtcr.setGroupKey(g1.getKey());
        util.collections.addGroupToCollection(c1.getKey(), agtcr, null); // add group to parent collection

        AddFileToCollectionRequest aftcr = new AddFileToCollectionRequest();
        aftcr.setFileKey(f1.getKey());
        util.collections.addFileToCollection(c1.getKey(), aftcr, null); // add file to parent

        HiarcFile ff = util.files.getFile(f1.getKey(), u1.getKey());
        Assert.assertEquals(f1, ff);

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Create File with Metadata
     *
     * 
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void createFileWithMetadataTest() throws ApiException, ParseException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(util.getTestMetadata()), f, null);
        HiarcFile ff = util.files.getFile(f1.getKey(), null);
        Assert.assertEquals(f1, ff);

        String newName = "New Name.txt";
        String newDescription = "New description";
        UpdateFileRequest ufr = new UpdateFileRequest();
        ufr.setName(newName);
        ufr.setDescription(newDescription);

        HiarcFile uf = util.files.updateFile(f1.getKey(), ufr, null);
        Assert.assertEquals(uf.getName(), newName);
        Assert.assertEquals(uf.getDescription(), newDescription);
        Assert.assertTrue(uf.getModifiedAt().isAfter(uf.getCreatedAt()));

        Assert.assertEquals(f1, ff);
        Assert.assertTrue(util.assertMetadataEqual(ff.getMetadata(), util.getTestMetadata()));
        Assert.assertTrue(util.assertMetadataEqual(f1.getMetadata(), ff.getMetadata()));

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Update File with Metadata
     *
     * 
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void updateFileWithMetadataTest() throws ApiException, ParseException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(util.getTestMetadata()), f, null);
        HiarcFile ff = util.files.getFile(f1.getKey(), null);
        Assert.assertEquals(f1, ff);

        UpdateFileRequest ufr = new UpdateFileRequest();
        ufr.setMetadata(util.updatedTestMetadata());

        HiarcFile uf = util.files.updateFile(f1.getKey(), ufr, null);
        Assert.assertTrue(uf.getModifiedAt().isAfter(uf.getCreatedAt()));

        Assert.assertTrue(util.assertMetadataEqual(uf.getMetadata(), util.updatedTestMetadata()));

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }

    /**
     * Null Out File Metadata
     *
     * 
     *
     * @throws ApiException   if the Api call fails
     * @throws ParseException
     */
    @Test
    public void nullOutMetadataTest() throws ApiException, ParseException {
        File f = new File(util.ABS_TEST_FILE_PATH);
        HiarcFile f1 = util.files.createFile(util.createFile(util.getTestMetadata()), f, null);
        HiarcFile ff = util.files.getFile(f1.getKey(), null);
        Assert.assertEquals(f1, ff);

        UpdateFileRequest ufr = new UpdateFileRequest();
        ufr.putMetadataItem("department", null);
        ufr.putMetadataItem("quotaCarrying", null);
        HiarcFile updated = util.files.updateFile(f1.getKey(), ufr, null);
        Assert.assertTrue(updated.getMetadata().keySet().size() == 3);

        UpdateFileRequest ufr2 = new UpdateFileRequest();
        ufr2.putMetadataItem("targetRate", null);
        ufr2.putMetadataItem("level", null);
        ufr2.putMetadataItem("startDate", null);
        HiarcFile updated2 = util.files.updateFile(f1.getKey(), ufr2, null);
        Assert.assertNull(updated2.getMetadata());

        util.files.deleteFile(f1.getKey(), null);
        Assert.assertThrows(ApiException.class, () -> {
            util.files.getFile(f1.getKey(), null);
        });
    }
}
