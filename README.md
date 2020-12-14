<h2> Role of Data Analytics in E-commerce</h2>
<p>
Data Analytics is becoming increasing important for all businesses espicially e-commerce websites. Collecting and analyzing statistical data can help e-commerce platforms to understand customer behavior, latest trands, sales activities and hence, gain an advantage over other competitors who do not have this information. 
  </p>
  
  <h2> Apache Spark </h2>
  <p> The Spark shell makes it easy to do interactive data analysis using Python or Scala. Spark SQL has a separate SQL shell that can be used to do data exploration using SQL. Spark is widely used by data scientists to handle problems with large data sizes. </p>
  
  <h2> Overview </h2>
 <p>The scope of this project includes the following: </p>
 <ol>
  <li>Save raw data</li>
<li>Runs Spark job to transform raw data to analysed data which is then flushed to Cassandra database</li>
  <li> Retrieve analyzed data from the respective Cassandra tables</li>
</ol>

<h2>Data Modeling in Cassandra</h2>
<p> Designing tables in Cassandra is an extremely important part of the Data analytics. </br>
  Refer <link>https://erarica-mehra.medium.com/simple-strategy-for-data-modeling-in-cassandra-98a726ad3a98</link> to learn to design data models in cassandra</p>

<h2>Getting Started</h2>
<ol>
<li>Clone from git and import into your IDE</li>
<li>Download Cassandra from official website. </br>
  Set up keyspace and table from the data-model.cql. (Refer application.properties)</li>
  <li>Add raw data yourself or copy from csv provided.</li>
 <li> Run the spark job using the mapping <code>/refresh</code>. Alternative you can set up a chrone job to run spark job daily.</li>
</ol>

<h2>Endpoints </h2>
<ul>
  <li><code>/analytics/save</code> : Save Raw Data </li>
  <li><code>/analytics/products/{year}</code> : Fetches seller performance data within the selected period </li>
  <li> <code>/analytics/productAnalysisOfASeller/{year}/{seller} </code> : Fetches product performance data of the specified sellers within the selected period </li>
    <li> <code>/analytics/viewProducts/{year} </code> : Fetches product info of the specified products within the selected period </li>
     <li> <code>/analytics/refresh </code> : Runs Spark job to transform raw data to analyzed data and flushed into cassandra tables </li>

  </ul>
  
  <h2>Built With:</h2>
  <ul>
    <li> Java</li>
   <li> Apache Spark</li>
   <li> Apache Cassandra</li>
  </ul>
  
  <h2>Author</h2>
 <p> Erarica Mehra</p>
