/*
 * Copyright 2014 Massachusetts General Hospital
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.annotopia.grails.connectors.plugin.client.annotatorjs.encoding

import grails.converters.JSON

import org.apache.jena.riot.RDFDataMgr
import org.apache.jena.riot.RDFLanguages
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.Before
import org.junit.Test

import com.hp.hpl.jena.query.Dataset
import com.hp.hpl.jena.rdf.model.Model

/**
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
class AnnotatorJsStorageTests extends GroovyTestCase {

	def annotatorJsEncoderService;
	def annotatorJsStorageService;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testSimpleAnnotationCreation001() {
		def json = '{"tags":[],"text":"safsafsa","quote":"hall","ranges":[{"endOffset":16,"start":"/div[1]/h4[1]","end":"/div[1]/h4[1]","startOffset":12}],"permissions":{"update":[],"admin":[],"delete":[],"read":[]},"uri":"http://afdemo.aws.af.cm/annotator/index","user":"jmiranda", "id":"32423432"}';
		
		JSONObject jsonObject = JSON.parse(json);
		
		Model model = annotatorJsEncoderService.encode(jsonObject)
		assertNotNull(model);
		
		log.info "==========================="
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		RDFDataMgr.write(outputStream, model, RDFLanguages.JSONLD);
		log.info outputStream.toString();
		
		log.info "==========================="
		
		Dataset set = null;
		try {
			log.info("start: " +set)
			set = annotatorJsStorageService.save("testkey", System.currentTimeMillis(), model);
			log.info("after: " +set)
			outputStream = new ByteArrayOutputStream();
			RDFDataMgr.write(outputStream, set, RDFLanguages.JSONLD);
			log.info outputStream.toString();
		} catch(Exception e) {
			log.error "Error:" + e.getMessage(),e;
		}
		assertNotNull(set);
	}
}
