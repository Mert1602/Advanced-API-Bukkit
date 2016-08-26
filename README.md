Advanced-API-Bukkit
=

### Maven

Add the following to your pom.xml

```xml
<project>

	<!-- other settings -->

	<repositories>
		<repository>
			<id>Mert1602-Advanced-API-Bukkit-mvn-repo</id>
			<url>https://raw.githubusercontent.com/Mert1602/Advanced-API-Bukkit/mvn-repo/</url>
			<snapshots>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
			</snapshots>
		</repository>
	</repositories>

	<dependencies>
		<dependency>
			<groupId>me.mert1602</groupId>
			<artifactId>advanced-api-bukkit</artifactId>
			<version>[1.0.0,2.0.0)</version>
		</dependency>
	</dependencies>

</project>
```