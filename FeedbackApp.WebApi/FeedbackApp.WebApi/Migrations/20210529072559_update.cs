using Microsoft.EntityFrameworkCore.Migrations;

namespace FeedbackApp.WebApi.Migrations
{
    public partial class update : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<string>(
                name: "Value",
                table: "Answers",
                type: "nvarchar(max)",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.UpdateData(
                table: "TypeFeedbacks",
                keyColumn: "TypeId",
                keyValue: 1,
                column: "Name",
                value: "Online");

            migrationBuilder.UpdateData(
                table: "TypeFeedbacks",
                keyColumn: "TypeId",
                keyValue: 2,
                column: "Name",
                value: "Offline");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterColumn<int>(
                name: "Value",
                table: "Answers",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(string),
                oldType: "nvarchar(max)",
                oldNullable: true);

            migrationBuilder.UpdateData(
                table: "TypeFeedbacks",
                keyColumn: "TypeId",
                keyValue: 1,
                column: "Name",
                value: "InComplete");

            migrationBuilder.UpdateData(
                table: "TypeFeedbacks",
                keyColumn: "TypeId",
                keyValue: 2,
                column: "Name",
                value: "Complete");
        }
    }
}
