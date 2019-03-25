package com.github.cafdataprocessing.services.staging.utils;
/*
 * Copyright 2015-2018 Micro Focus or one of its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;

public final class JsonMinifierTest {

    @Test
    public void minifyJsonTest() throws Exception {
        System.out.println("minifyJsonTest...");
        final InputStream inputStream = JsonMinifierTest.class.getResource("/batch1.json").openStream();
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifyJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifyJsonTest", minifiedJson.contains("/etc/store/batches/acme-com/completed/test_batch/files"));
    }

    @Test
    public void minifyNoRefJsonTest() throws Exception {
        System.out.println("minifyNoRefJsonTest...");
        String testJson = 
            "{"
        + "    'document': {"
        + "      'reference': 'batch2.msg',"
        + "      'fields': {"
        + "        'FROM': {'data': 'Mark Roberts'},"
        + "        'TO': {'data': 'Gene Simmons'},"
        + "        'SUBJECT': {'data': 'Favourite book'},"
        + "        'CONTENT': {'data': 'This is the book that popularised the use of the phrase Merry Christmas.'}"
        + "      }"
        + "    }"
        + "  }";
        testJson = testJson.replaceAll("'", "\"");
        final InputStream inputStream = new ByteArrayInputStream(testJson.getBytes("UTF-8"));
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifyNoRefJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifyNoRefJsonTest", !minifiedJson.contains("/etc/store/batches/acme-com/completed/test_batch/files"));
    }

    @Test
    public void minifyUTFEncodingOnlyJsonTest() throws Exception {
        System.out.println("minifyUTFEncodingOnlyJsonTest...");
        String testJson = 
            "{"
        + "    'document': {"
        + "      'reference': 'batch2.msg',"
        + "      'fields': {"
        + "        'FROM': {'encoding': 'utf-8', 'data': 'Mark Roberts'},"
        + "        'TO': {'data': 'Gene Simmons'},"
        + "        'SUBJECT': {'data': 'Favourite book'},"
        + "        'CONTENT': {'data': 'This is the book that popularised the use of the phrase Merry Christmas.'}"
        + "      }"
        + "    }"
        + "  }";
        testJson = testJson.replaceAll("'", "\"");
        final InputStream inputStream = new ByteArrayInputStream(testJson.getBytes("UTF-8"));
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifyUTFEncodingOnlyJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifyUTFEncodingOnlyJsonTest", !minifiedJson.contains("/etc/store/batches/acme-com/completed/test_batch/files"));
    }

    @Test
    public void minifyStorageRefJsonTest() throws Exception {
        System.out.println("minifyStorageRefJsonTest...");
        String testJson = 
            "{"
        + "    'document': {"
        + "      'reference': 'batch2.msg',"
        + "      'fields': {"
        + "        'FROM': {'data': 'Mark Roberts', 'encoding': 'utf-8'},"
        + "        'TO': {'data': 'Gene Simmons'},"
        + "        'SUBJECT': {'data': 'Favourite book'},"
        + "        'CONTENT': {'data': 'This is the book that popularised the use of the phrase Merry Christmas.'},"
        + "        'BINARY_FILE': {"
        + "            'data': 'http://www.lang.nagoya-u.ac.jp/~matsuoka/misc/urban/cd-carol.doc',"
        + "            'encoding': 'storage_ref'"
        + "           }"
        + "      }"
        + "    }"
        + "  }";
        testJson = testJson.replaceAll("'", "\"");
        final InputStream inputStream = new ByteArrayInputStream(testJson.getBytes("UTF-8"));
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifyStorageRefJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifyStorageRefJsonTest", !minifiedJson.contains("/etc/store/batches/acme-com/completed/test_batch/files"));
    }

    @Test
    public void minifyLocalRefJsonTest() throws Exception {
        System.out.println("minifyLocalRefJsonTest...");
        String testJson = 
            "{"
        + "    'document': {"
        + "      'reference': 'batch2.msg',"
        + "      'fields': {"
        + "        'FROM': {'data': 'Mark Roberts', 'encoding': 'utf-8'},"
        + "        'TO': {'data': 'Gene Simmons'},"
        + "        'SUBJECT': {'data': 'Favourite book'},"
        + "        'CONTENT': {'data': 'This is the book that popularised the use of the phrase Merry Christmas.'},"
        + "        'BINARY_FILE': {"
        + "            'data': 'http://www.lang.nagoya-u.ac.jp/~matsuoka/misc/urban/cd-carol.doc',"
        + "            'encoding': 'storage_ref'"
        + "           },"
        + "        'COVER_PIC': {"
        + "            'encoding': 'local_ref',"
        + "            'data': 'Front_Cover.jpg'"
        + "          }"
        + "      }"
        + "    }"
        + "  }";
        testJson = testJson.replaceAll("'", "\"");
        final InputStream inputStream = new ByteArrayInputStream(testJson.getBytes("UTF-8"));
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifyLocalRefJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifyLocalRefJsonTest", minifiedJson.contains("/etc/store/batches/acme-com/completed/test_batch/files"));
    }

    @Test
    public void minifySimpleDocJsonTest() throws Exception {
        System.out.println("minifySimpleDocJsonTest...");
        String testJson = 
            "{"
        + "    'document': {"
        + "      'reference': 'batch2.msg',"
        + "      'fields': {"
        + "        'STATUS': 'DELETED'"
        + "      }"
        + "    }"
        + "  }";
        testJson = testJson.replaceAll("'", "\"");
        final InputStream inputStream = new ByteArrayInputStream(testJson.getBytes("UTF-8"));
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifySimpleDocJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifySimpleDocJsonTest", !minifiedJson.contains("/etc/store/batches/acme-com/completed/test_batch/files"));
    }

    @Test
    public void minifyBase64EncodingJsonTest() throws Exception {
        System.out.println("minifyBase64EncodingJsonTest...");
        String testJson = 
            "{"
        + "    'document': {"
        + "      'reference': 'batch2.msg',"
        + "      'fields': {"
        + "        'FROM': {'data': 'Mark Roberts', 'encoding': 'utf-8'},"
        + "        'TO': {'data': 'Gene Simmons'},"
        + "        'SUBJECT': {'data': 'Favourite book'},"
        + "        'CONTENT': {'data': 'This is the book that popularised the use of the phrase Merry Christmas.'},"
        + "        'BINARY_FILE': {"
        + "            'data': 'http://www.lang.nagoya-u.ac.jp/~matsuoka/misc/urban/cd-carol.doc',"
        + "            'encoding': 'storage_ref'"
        + "           },"
        + "        'COVER_PIC': {"
        + "            'encoding': 'base64',"
        + "            'data': 'QSBDaHJpc3RtYXMgQ2Fyb2wgQm9vayBDb3Zlcg=='"
        + "          }"
        + "      }"
        + "    }"
        + "  }";
        testJson = testJson.replaceAll("'", "\"");
        final InputStream inputStream = new ByteArrayInputStream(testJson.getBytes("UTF-8"));
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifyBase64EncodingJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifyBase64EncodingJsonTest", minifiedJson.contains("base64"));
    }

    @Test
    public void minifyArrayFieldJsonTest() throws Exception {
        System.out.println("minifyArrayFieldJsonTest...");
        final InputStream inputStream = JsonMinifierTest.class.getResource("/batch2.json").openStream();
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifyArrayFieldJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifyArrayFieldJsonTest", !minifiedJson.contains("/etc/store/batches/acme-com/completed/test_batch/files"));
    }

    @Test
    public void minifyNestedDocJsonTest() throws Exception {
        System.out.println("minifyNestedDocJsonTest...");
        final InputStream inputStream = JsonMinifierTest.class.getResource("/batch3.json").openStream();
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        JsonMinifier.minifyJson(inputStream, outStream, "/etc/store/batches/acme-com/completed/test_batch/files");
        final String minifiedJson = outStream.toString("UTF-8");
        System.out.println("minifyNestedDocJsonTest : Minified Json : " + minifiedJson);
        assertTrue("minifyNestedDocJsonTest", minifiedJson.contains("subdocuments"));
    }
}
