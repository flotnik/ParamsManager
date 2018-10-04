package com.params_manager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

public class DataBlocks {

    List<DataBlock> blocks;

    public DataBlocks() {
        this.blocks = new ArrayList<>();
    }

    public List<DataBlock> getBlocks() {
        return blocks;
    }

    public DataBlock GetBlock(String block_name) throws Exception {
        for (DataBlock p : blocks) {
            if (p.getName().equals(block_name)) {
                return p;
            }
        }
        throw new Exception("Block with name " + block_name + " wasn't found");
    }

    public void AddBlock(DataBlock b) throws Exception {
        for (DataBlock p : blocks) {
            if (p.getName().equals(b.getName())) {
                throw new Exception("You trying to add another block with the same name " + b.getName());
            }
        }
        blocks.add(b);
    }

    public void ResolveLinks() throws Exception {
        for (DataBlock b : blocks) {
            for (ParamLink pl : b.param_links) {
                DataBlock b2 = GetBlock(pl.getBlock_name());
                Param p2 = b2.findParamByname(pl.getName());
                pl.setValue(p2.getValue());
            }
        }
    }

    public void parseBlocksFromXMLString(String data_xml, File rules_file) throws IOException, SAXException {
        DigesterLoader loader = DigesterLoader.newLoader(new MyRulesModule(rules_file));
        Digester digester = loader.newDigester();
        digester.push(this);
        digester.parse(new StringReader(data_xml));
    }

    /***
     * Reads file as xml document and than tries to load params
     * @param data_xml - File object
     * @throws Exception
     */
    public void loadParams(File data_xml, File rules_file) throws Exception {
        //System.out.print("Reading xml with params...");
        SAXReader reader = new SAXReader();
        Document document = reader.read(data_xml);
        //System.out.println("OK");

        System.out.print("Loading datablocks ...");
        parseBlocksFromXMLString(document.getRootElement().asXML(), rules_file);
        System.out.println("OK");
        ResolveLinks();
    }

    /***
     * Read file as string and than tries to load params
     * @param data_xml - File object
     * @throws Exception
     */
    public void loadParams2(File data_xml, File rules_file) throws Exception {
        System.out.print("Reading xml with params...");
        String xml_string = FileUtils.readFileToString(data_xml, Charset.forName("utf-8"));
        System.out.println("OK");

        System.out.print("Loading datablocks ...");
        parseBlocksFromXMLString(xml_string, rules_file);
        ResolveLinks();
        System.out.println("OK");
    }

    public void loadParamsFromInputStream(InputStream is, File rules_file) throws Exception {
        System.out.print("Reading xml with params...");
        String xml_string = IOUtils.toString(is, Charset.forName("utf-8"));
        System.out.println("OK");

        System.out.print("Validating...");
        DocumentHelper.parseText(xml_string);
        System.out.println("OK");

        System.out.print("Loading datablocks ...");
        parseBlocksFromXMLString(xml_string, rules_file);
        ResolveLinks();
        System.out.println("OK");
    }
}
