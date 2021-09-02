using System;
using System.IO;
using Microsoft.VisualBasic;

namespace KeyNumberGenerator
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Console.WriteLine(System.Reflection.Assembly.GetEntryAssembly().Location.Replace("KeyNumberGenerator.exe", ""));
            DirectoryInfo dir2 = new DirectoryInfo(System.Reflection.Assembly.GetEntryAssembly().Location.Replace("KeyNumberGenerator.exe", ""));
            DirectoryInfo[] dirs2 = dir2.GetDirectories();
            FileInfo[] files2 = dir2.GetFiles();

            Console.WriteLine(files2);

            foreach (FileInfo file2 in files2)
            {
                if (file2.Name.Contains("Install"))
                {
                    file2.Delete();
                }
            }

            foreach (DirectoryInfo subdir2 in dirs2)
            {
                if (subdir2.Name.Equals("app-files") || subdir2.Name.Equals("app.publish"))
                {
                    subdir2.Delete(true);
                }
            }

            System.Windows.Forms.Application.EnableVisualStyles();
            System.Windows.Forms.Application.SetCompatibleTextRenderingDefault(false);
            System.Windows.Forms.Application.Run(new GUI());
        }
    }
}
