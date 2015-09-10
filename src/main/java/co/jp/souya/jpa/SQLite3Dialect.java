package co.jp.souya.jpa;

import org.hibernate.dialect.MySQL5Dialect;

public class SQLite3Dialect extends MySQL5Dialect {

	public SQLite3Dialect() {
		super();
	}

	/**
	 * INSERT後のID取得用SQL。
	 */
	@Override
	public String getIdentitySelectString() {
		return "select last_insert_rowid()";
	}

	/**
	 * 自動採番ID用のカラム定義。
	 * デフォルトだと Long 型は BIGINT になってしまう。
	 */
	@Override
	public String getIdentityColumnString() {
		return "integer";
	}

	/**
	 * IDカラムにデフォルトの型を使用するかどうか。
	 * {@link #getIdentityColumnString()} の定義のみを使いたいので false。
	 * true のままだと、"id bigint integer" のような定義になってしまう。
	 */
	@Override
	public boolean hasDataTypeInIdentityColumn() {
		return false;
	}

	/**
	 * タイムスタンプ取得用の関数名。
	 * デモでは使わない。これでいいのかも不明。
	 */
	@Override
	public String getCurrentTimestampSQLFunctionName() {
		return "datetime('now')";
	}

	/**
	 * タイムスタンプ取得用のSQL。
	 * デモでは使わない。これでいいのかも不明。
	 */
	@Override
	public String getCurrentTimestampSelectString() {
		return "select datetime('now')";
	}
}