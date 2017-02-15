import org.apache.spark.sql.SparkSession
// define main method (scala entry point)
object montepi {
  def main(args: Array[String]): Unit = {

    // initialise spark session (running in "local" mode)
    val sparkSession = SparkSession.builder
      .master("local")
      .appName("monte pi")
       .getOrCreate()

    val NUM_SAMPLES = 1000000000
    val sc = sparkSession.sparkContext

    val count = sc.parallelize(1 to NUM_SAMPLES).map{i =>
    val x = Math.random()
    val y = Math.random()
    if (x*x + y*y < 1) 1 else 0
}.reduce(_ + _)

println("Pi is roughly " + 4.0 * count / NUM_SAMPLES)
    
    // terminate underlying spark context
    sparkSession.stop()
  }
}
