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
package org.annotopia.grails.connectors.plugin.client.annotatorjs.encoding;

/**
 * List of classes and properties of the Dublin Core Types vocabulary.
 * Website: http://dublincore.org/documents/dcmi-terms/
 * 
 * @author Paolo Ciccarese <paolo.ciccarese@gmail.com>
 */
public interface IDublinCoreTypesVocabulary {

	public static final String NAMESPACE = "http://purl.org/dc/dcmitype/";
	
	public static final String CLASS_TEXT_URI = NAMESPACE + "Text";
}
