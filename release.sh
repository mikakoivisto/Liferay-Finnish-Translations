#!/bin/sh
java -cp . Ascii2Native ./Language_en.properties ./Language_fi.properties
jar cvMf Translations.zip  Language_fi.properties Language_fi.properties.native
