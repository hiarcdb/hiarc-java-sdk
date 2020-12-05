package com.hiarcdb.client.integrationTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.hiarcdb.client.ApiClient;
import com.hiarcdb.client.Configuration;
import com.hiarcdb.client.api.AdminApi;
import com.hiarcdb.client.api.ClassificationApi;
import com.hiarcdb.client.api.CollectionApi;
import com.hiarcdb.client.api.FileApi;
import com.hiarcdb.client.api.GroupApi;
import com.hiarcdb.client.api.LegalHoldApi;
import com.hiarcdb.client.api.RetentionPolicyApi;
import com.hiarcdb.client.api.TokenApi;
import com.hiarcdb.client.api.UserApi;
import com.hiarcdb.client.auth.ApiKeyAuth;
import com.hiarcdb.client.auth.HttpBearerAuth;
import com.hiarcdb.client.model.CreateClassificationRequest;
import com.hiarcdb.client.model.CreateCollectionRequest;
import com.hiarcdb.client.model.CreateFileRequest;
import com.hiarcdb.client.model.CreateGroupRequest;
import com.hiarcdb.client.model.CreateLegalHoldRequest;
import com.hiarcdb.client.model.CreateRetentionPolicyRequest;
import com.hiarcdb.client.model.CreateUserRequest;

import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;

public class HiarcTestUtil {
    private int currFile = 1;
    private int currColl = 1;
    private int currUser = 1;
    private int currGroup = 1;
    private int currRetentionPolicy = 1;
    private int currLegalHold = 1;
    private int currClassification = 1;

    public String AWS_EAST_STORAGE = "hiarc-aws-s3-east";
    public String AWS_WEST_STORAGE = "hiarc-aws-s3-west";
    public String AZURE_STORAGE_1 = "hiarc-azure-blob-1";
    public String AZURE_STORAGE_2 = "hiarc-azure-blob-2";
    public String GOOGLE_EAST_STORAGE = "hiarc-google-storage-east";
    public String GOOGLE_WEST_STORAGE = "hiarc-google-storage-west";
    public ArrayList<String> All_STORAGE_SERVICES = new ArrayList<String>(Arrays.asList(AWS_EAST_STORAGE,
            AWS_WEST_STORAGE, AZURE_STORAGE_1, AZURE_STORAGE_2, GOOGLE_EAST_STORAGE, GOOGLE_WEST_STORAGE));

    public final long RETENTION_PERIOD_MINUTE = 60L;
    public final long RETENTION_PERIOD_HOUR = 3600L;
    public final long RETENTION_PERIOD_DAY = 86400L;
    public final long RETENTION_PERIOD_MONTH = 2678400L;
    public final long RETENTION_PERIOD_YEAR = 31557600L;
    public final long RETENTION_PERIOD_MAX = 3155760000L;

    private final String ADMIN_KEY = "<ADMIN_API_KEY>";
    public final String ADMIN_NAME = "admin";

    public final int LARGE_ENTITY_COUNT = 10;

    public AdminApi admin;
    public UserApi users;
    public GroupApi groups;
    public FileApi files;
    public CollectionApi collections;
    public ClassificationApi classifications;
    public LegalHoldApi legalHolds;
    public RetentionPolicyApi retentionPolicies;
    public TokenApi tokens;

    public Path TEST_FILE_PATH = Paths.get("src", "test", "resources", "TestFiles", "Test.txt");
    public String ABS_TEST_FILE_PATH = TEST_FILE_PATH.toFile().getAbsolutePath();

    public Path NEW_VERSION_TEST_FILE_PATH = Paths.get("src", "test", "resources", "TestFiles", "NewVersionOfTest.txt");
    public String NEW_VERSION_ABS_TEST_FILE_PATH = NEW_VERSION_TEST_FILE_PATH.toFile().getAbsolutePath();

    public Path TEST_DOWNLOAD_FILE_PATH = Paths.get("src", "test", "resources", "TestFiles", "downloadedTest.txt");
    public String ABS_TEST_DOWNLOAD_FILE_PATH = TEST_DOWNLOAD_FILE_PATH.toFile().getAbsolutePath();

    public int DEFAULT_EXPIRES_IN_SECONDS = 10;

    private String userDescription = "This user was kissed by a rose on the grey.";
    private String fileDescription = "This file redacted by Carl Grimm.";
    private String collectionDescription = "This collection is a Bjork cover band.";
    private String classificationDescription = "This classification karaokes 4 Non Blondes.";
    private String legalHoldDescription = "This legal hold paints house boats.";
    private String retentionPolicyDescription = "This retention policy owns a biergarten.";
    private String groupDescription = "This group loves John Barber";

    public HiarcTestUtil() {
        ApiClient api = createAdminConfig();
        admin = new AdminApi(api);
        users = new UserApi(api);
        groups = new GroupApi(api);
        files = new FileApi(api);
        collections = new CollectionApi(api);
        classifications = new ClassificationApi(api);
        legalHolds = new LegalHoldApi(api);
        retentionPolicies = new RetentionPolicyApi(api);
        tokens = new TokenApi(api);
    }

    public ApiClient createAdminConfig() {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:5000");

        HttpBearerAuth jwtApiKeyAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
        jwtApiKeyAuth.setBearerToken("");
        ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
        AdminApiKeyAuth.setApiKey(ADMIN_KEY);
        return defaultClient;
    }

    public ApiClient createJWTConfig(String token) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:5000");

        ApiKeyAuth AdminApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("AdminApiKeyAuth");
        AdminApiKeyAuth.setApiKey("");
        HttpBearerAuth jwtApiKeyAuth = (HttpBearerAuth) defaultClient.getAuthentication("JWTBearerAuth");
        jwtApiKeyAuth.setBearerToken(token);
        return defaultClient;
    }

    public CreateUserRequest createUser() {
        String key = generateKey("user");
        String name = getPunkName();
        CreateUserRequest cur = new CreateUserRequest();
        cur.setKey(key);
        cur.setName(name);
        cur.setDescription(userDescription);
        return cur;
    }

    public CreateUserRequest createUser(Map<String, Object> metadata) {
        CreateUserRequest cur = createUser();
        cur.setMetadata(metadata);
        return cur;
    }

    public CreateRetentionPolicyRequest createRetentionPolicy() {
        String key = generateKey("retentionPolicy");
        String name = String.format("retention-policy-%s", key);
        CreateRetentionPolicyRequest crpr = new CreateRetentionPolicyRequest();
        crpr.setKey(key);
        crpr.setName(name);
        crpr.setDescription(retentionPolicyDescription);
        return crpr;
    }

    public CreateRetentionPolicyRequest createRetentionPolicy(Map<String, Object> metadata) {
        CreateRetentionPolicyRequest crpr = createRetentionPolicy();
        crpr.setMetadata(metadata);
        return crpr;
    }

    public CreateRetentionPolicyRequest createRetentionPolicy(Long expiresInSeconds) {
        CreateRetentionPolicyRequest crpr = createRetentionPolicy();
        crpr.setSeconds(expiresInSeconds);
        return crpr;
    }

    public CreateRetentionPolicyRequest createRetentionPolicy(Long expiresInSeconds, Map<String, Object> metadata) {
        CreateRetentionPolicyRequest crpr = createRetentionPolicy();
        crpr.setSeconds(expiresInSeconds);
        crpr.setMetadata(metadata);
        return crpr;
    }

    public CreateLegalHoldRequest createLegalHold() {
        String key = generateKey("legalHold");
        String name = String.format("legal-hold-%s", key);
        CreateLegalHoldRequest clhr = new CreateLegalHoldRequest();
        clhr.setKey(key);
        clhr.setName(name);
        clhr.setDescription(legalHoldDescription);
        return clhr;
    }

    public CreateLegalHoldRequest createLegalHold(Map<String, Object> metadata) {
        CreateLegalHoldRequest clhr = createLegalHold();
        clhr.setMetadata(metadata);
        return clhr;
    }

    public CreateFileRequest createFile() {
        return createFile("txt");
    }

    public CreateFileRequest createFile(String extenstion) {
        String key = generateKey("file");
        String name = getPunkSong();
        CreateFileRequest cfr = new CreateFileRequest();
        cfr.setKey(key);
        cfr.setName(String.format("%s.%s", name, extenstion));
        cfr.setDescription(fileDescription);
        return cfr;
    }

    public CreateFileRequest createFile(Map<String, Object> metadata) {
        CreateFileRequest cfr = createFile();
        cfr.setMetadata(metadata);
        return cfr;
    }

    public CreateGroupRequest createGroup() {
        String key = generateKey("group");
        String name = getPunkGroup();
        CreateGroupRequest cgr = new CreateGroupRequest();
        cgr.setKey(key);
        cgr.setName(name);
        cgr.setDescription(groupDescription);
        return cgr;
    }

    public CreateGroupRequest createGroup(Map<String, Object> metadata) {
        CreateGroupRequest cgr = createGroup();
        cgr.setMetadata(metadata);
        return cgr;
    }

    public CreateClassificationRequest createClassification() {
        String key = generateKey("classification");
        String name = getPunkClassifications();
        CreateClassificationRequest ccr = new CreateClassificationRequest();
        ccr.setKey(key);
        ccr.setName(name);
        ccr.setDescription(classificationDescription);
        return ccr;
    }

    public CreateClassificationRequest createClassification(Map<String, Object> metadata) {
        CreateClassificationRequest ccr = createClassification();
        ccr.setMetadata(metadata);
        return ccr;
    }

    public CreateCollectionRequest createCollection() {
        String key = generateKey("collection");
        String name = getPunkAlbum();
        CreateCollectionRequest ccr = new CreateCollectionRequest();
        ccr.setKey(key);
        ccr.setName(name);
        ccr.setDescription(collectionDescription);
        return ccr;
    }

    public CreateCollectionRequest createCollection(Map<String, Object> metadata) {
        CreateCollectionRequest ccr = createCollection();
        ccr.setMetadata(metadata);
        return ccr;
    }

    public Date parseDateString(String time) {
        return DateTimeUtils.toDate(Instant.parse(time));
    }

    public Boolean assertMetadataEqual(Map<String, Object> m1, Map<String, Object> m2) {
        Boolean isEqual = false;
        if (m1.get("startDate").getClass() == String.class) {
            m1.put("startDate", parseDateString(m1.get("startDate").toString()));
        }
        if (m2.get("startDate").getClass() == String.class) {
            m2.put("startDate", parseDateString(m2.get("startDate").toString()));
        }
        if (m1.get("startDate").getClass() == Date.class && m2.get("startDate").getClass() == Date.class) {
            Date d1 = (Date) m1.get("startDate");
            Date d2 = (Date) m2.get("startDate");
            isEqual = (d1.compareTo(d2) == 0) ? true : false;
            if (isEqual == false) {
                return isEqual;
            }
        }
        for (String k : m1.keySet()) {
            if (k.equals("startDate")) {
                continue;
            }
            if (k.equals("department")) {
                isEqual = ((String) m1.get(k)).equals((String) m2.get(k));
                if (isEqual == false) {
                    return isEqual;
                }
            }
            if (k.equals("quotaCarrying")) {
                isEqual = ((Boolean) m1.get(k)).equals((Boolean) m2.get(k));
                if (isEqual == false) {
                    return isEqual;
                }
            }
            if (k.equals("targetRate")) {
                isEqual = ((Double) m1.get(k)).equals((Double) m2.get(k));
                if (isEqual == false) {
                    return isEqual;
                }
            }
            if (k.equals("level")) {
                int i1 = 1;
                int i2 = 0;
                if (m1.get(k) instanceof Double) {
                    Double d = (Double) m1.get(k);
                    i1 = d.intValue();
                }
                if (m1.get(k) instanceof Integer) {
                    i1 = (int) m1.get(k);
                }
                if (m2.get(k) instanceof Double) {
                    Double d = (Double) m2.get(k);
                    i2 = d.intValue();
                }
                if (m2.get(k) instanceof Integer) {
                    i2 = (int) m2.get(k);
                }
                isEqual = i1 == i2;
                if (isEqual == false) {
                    return isEqual;
                }
            }
        }
        return isEqual;
    }

    public Map<String, Object> updatedTestMetadata() throws ParseException {
        Map<String, Object> m = new HashMap<>();
        m.put("department", "support");
        m.put("quotaCarrying", false);
        m.put("targetRate", 7.271);
        m.put("level", 2);
        String time = "2020-02-25T22:33:50.134Z";
        Date d = parseDateString(time);
        m.put("startDate", d);
        return m;
    }

    public Map<String, Object> getTestMetadata() throws ParseException {
        Map<String, Object> m = new HashMap<>();
        m.put("department", "sales");
        m.put("quotaCarrying", true);
        m.put("targetRate", 4.234);
        m.put("level", (Integer) 3);
        String time = "2020-02-29T22:33:50.134Z";
        Date d = parseDateString(time);
        m.put("startDate", d);
        return m;
    }

    public String getPunkName() {
        Random rand = new Random();
        ArrayList<String> punkNames = new ArrayList<String>(
                Arrays.asList("Elivs Costello", "Debbie Harry", "David Byrne", "Siouxsie Sioux", "Vicki Peterson",
                        "Mark Mothersbaugh", "Josh Freese", "Dave Quackenbush", "Paul Westerberg", "Exene Cervenka",
                        "Jello Biafra", "Chris Hannah", "Kim Deal", "Ian Mackaye"));
        return punkNames.get(rand.nextInt(punkNames.size()));
    }

    public String getPunkGroup() {
        Random rand = new Random();
        ArrayList<String> punkGroups = new ArrayList<String>(
                Arrays.asList("The Attractions", "Blondie", "Talking Heads", "The Banshees", "The Bangles", "Devo",
                        "The Vandals", "The Replacements", "X", "Dead Kennedys", "Propagandhi", "Pixies", "Fugazi"));
        return punkGroups.get(rand.nextInt(punkGroups.size()));
    }

    public String getPunkAlbum() {
        Random rand = new Random();
        ArrayList<String> punkAlbums = new ArrayList<String>(Arrays.asList("This Year\\\'s Model", "Parallel Lines",
                "More Songs about Buildings and Food", "The Scream", "Different Light",
                "Q: Are We Not Men? A: We are Devo!", "Hitler Bad, Vandals Good", "Pleased to Meet Me", "Los Angeles",
                "Fresh Fruit for Rotting Vegetables", "Today\\\'s Empires, Tomorrow\\\'s Ashes", "Surfer Rosa",
                "Repeater"));
        return punkAlbums.get(rand.nextInt(punkAlbums.size()));
    }

    public String getPunkSong() {
        Random rand = new Random();
        ArrayList<String> punkSongs = new ArrayList<String>(Arrays.asList("No Action", "Heart of Glass",
                "Burning Down the House", "Cities in Dust", "Walk like an Egyptian", "Uncontrollable Urge", "Eurobarge",
                "Can\\\'t Hardly Wait", "Los Angeles", "Police Truck", "Ska Sucks", "Debaser", "Turnover"));
        return punkSongs.get(rand.nextInt(punkSongs.size()));
    }

    public String getPunkClassifications() {
        Random rand = new Random();
        ArrayList<String> punkClassifications = new ArrayList<String>(Arrays.asList("Power Pop", "New Wave", "Punk",
                "Ska", "Hardcore", "Emo", "Skate Punk", "Post Punk", "Screamo", "Grindcore", "Riot Grrl", "Pop Punk",
                "Post Hardcore", "Metalcore", "Mathcore", "Art Punk"));
        return punkClassifications.get(rand.nextInt(punkClassifications.size()));
    }

    public String generateKey(String prefix) {
        switch (prefix) {
            case "file":
                return String.format("%s-%s", prefix, currFile++);
            case "collection":
                return String.format("%s-%s", prefix, currColl++);
            case "user":
                return String.format("%s-%s", prefix, currUser++);
            case "group":
                return String.format("%s-%s", prefix, currGroup++);
            case "retentionPolicy":
                return String.format("%s-%s", prefix, currRetentionPolicy++);
            case "legalHold":
                return String.format("%s-%s", prefix, currLegalHold++);
            case "classification":
                return String.format("%s-%s", prefix, currClassification++);
            default:
                return String.format("%s-%s", prefix, UUID.randomUUID().toString());
        }
    }

    public String getFileChecksum(File file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        // Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        // Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        // Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        ;

        // close the stream; We don't need it now.
        fis.close();

        // Get the hash's bytes
        byte[] bytes = digest.digest();

        // This bytes[] has bytes in decimal format;
        // Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        // return complete hash
        return sb.toString();
    }

}
