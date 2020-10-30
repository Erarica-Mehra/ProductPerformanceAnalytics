package com.analytics.app.dao;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Repository;

import com.analytics.app.repository.SparkRepository;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import com.datastax.spark.connector.japi.SparkContextJavaFunctions;

@Repository
public class SparkDaoImpl implements SparkRepository{
	
	
	@Override
	public void transformData() {
		SQLContext sparkContext  = getSparkContext();
		transformRawToAnalysed(sparkContext);
		transformToProductsBySeller(sparkContext);
		transformToProductsByName(sparkContext);
		transformToProductsByStatus(sparkContext);
		transformToProductsByCategory(sparkContext);
	}
	
	
	private Dataset<Row> loadRawData(SQLContext ss) {
		Dataset<Row> raw = ss.read().format("org.apache.spark.sql.cassandra").option("keyspace", "aerobay").option("table", "raw_product_data").load();
	    return raw;
	}
	
	public void transformRawToAnalysed(SQLContext sparkContext) {
		Dataset<Row> raw = loadRawData(sparkContext);
		Dataset<Row> ds = raw.withColumn("day" ,raw.col("timestamp").cast("date")).select("year", "day", "category", "seller", "name", "id", "quantity", "price", "rating", "status", "description", "user");
        ds.write().mode("append").format("org.apache.spark.sql.cassandra").option("keyspace", "aerobay").option("table", "analysed_product").save();
	}
	
	public void transformToProductsBySeller(SQLContext sparkContext) {
		Dataset<Row> raw = loadRawData(sparkContext);
		Dataset<Row> ds = raw.withColumn("day" ,raw.col("timestamp").cast("date")).groupBy("year", "seller", "day","name", "id", "quantity", "price", "rating").count();
		Dataset<Row> productsBySeller = ds.select("year", "seller", "day","name", "id", "quantity", "price", "rating", "count");
		productsBySeller.write().mode("append").format("org.apache.spark.sql.cassandra").option("keyspace", "aerobay").option("table", "products_by_seller").save();
	}
	
	public void transformToProductsByName(SQLContext sparkContext) {
		Dataset<Row> raw = loadRawData(sparkContext);
		Dataset<Row> ds = raw.withColumn("day" ,raw.col("timestamp").cast("date")).groupBy("year", "name", "day", "id", "quantity", "price", "rating").count();
		Dataset<Row> productsByName = ds.select("year", "name", "day", "id", "quantity", "price", "rating", "count");
		productsByName.write().mode("append").format("org.apache.spark.sql.cassandra").option("keyspace", "aerobay").option("table", "products_by_name").save();
	}
	
	public void transformToProductsByStatus(SQLContext sparkContext) {
		Dataset<Row> raw = loadRawData(sparkContext);
		Dataset<Row> ds = raw.withColumn("day" ,raw.col("timestamp").cast("date")).groupBy("year", "status", "day", "id", "quantity", "price").count();
		Dataset<Row> productsByStatus = ds.select("year", "status", "day", "id", "quantity", "price", "count");
		productsByStatus.show();
		productsByStatus.write().mode("append").format("org.apache.spark.sql.cassandra").option("keyspace", "aerobay").option("table", "products_by_status").save();
	}
	
	public void transformToProductsByCategory(SQLContext sparkContext) {
		Dataset<Row> raw = loadRawData(sparkContext);
		Dataset<Row> ds = raw.withColumn("day" ,raw.col("timestamp").cast("date")).groupBy("year", "category", "day", "name", "id", "quantity" ).count();
		Dataset<Row> productsByCategory = ds.select("year", "category", "day", "name", "id", "quantity", "count");
		productsByCategory.write().mode("append").format("org.apache.spark.sql.cassandra").option("keyspace", "aerobay").option("table", "products_by_category").save();
	}
	
	private SQLContext getSparkContext() {
		SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("cassandra").setMaster("local");
		SparkContext context = new SparkContext(sparkConf);
		SparkContextJavaFunctions functions = CassandraJavaUtil.javaFunctions(context);
		//JavaRDD<CassandraRow> rdd = functions.cassandraTable("aerobay", "raw_product_data");
		SparkSession ss = SparkSession.builder().config(sparkConf).getOrCreate();
		SQLContext sqlContext = new org.apache.spark.sql.SQLContext(ss);
        return sqlContext;
	}

}
