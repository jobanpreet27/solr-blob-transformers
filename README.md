# Usage

Simply build the code and put it in your classpath of your Solr instance (Usually a lib folder as a shared folder for extensions would be better)

After that, go to your **db-config.xml** file, add 
> transformer="solr.transformer.BlobTransformer

to your **entity** tag. 

Then for the fields that you would like to apply the transformer

 blob="true" 

with the other transformers you would like to use.
An example **db-config.xml** will look like below with for this config:

```xml
<entity name="a" transformer="BlobTransformer" ... >
<field column="colname" blob="true" />
</entity>
```
Important Links
https://cwiki.apache.org/confluence/display/solr/DataImportHandlerFaq#head-149779b72761ab071c841879545256bdbbdc15d2
https://issues.apache.org/jira/browse/SOLR-1348