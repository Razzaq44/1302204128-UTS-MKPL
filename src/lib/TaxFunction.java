package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	private static final double taxRate = 0.05;
    private static final int maxChild = 3;
    private static final int maxMonthWorking = 12;
    private static final int thresholdNoTax = 54000000;
	private static final int noTaxMaried = 4500000;
    private static final int noTaxEachChild = 4500000;
    
	// Pembuatan variable baru digunakan untuk memudahkan readibility dan maintanability	
	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = 0;
		
		if (numberOfMonthWorking > maxMonthWorking) {
            throw new IllegalArgumentException("More than 12 months working per year");
        }
		
		numberOfChildren = Math.min(numberOfChildren, maxChild);
		
		if (isMarried) {
			tax = (int) Math.round(taxRate * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (thresholdNoTax + noTaxMaried + (numberOfChildren * noTaxEachChild))));
		}else {
			tax = (int) Math.round(taxRate * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - thresholdNoTax));
		}
		
		return Math.max(tax, 0);
			 
	}
	
}
