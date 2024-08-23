import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { take } from 'rxjs';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { GameService } from '../../../../core/services/game/game.service';
import { TranslateConfigModule } from '../../../../shared/modules/translate-config/translate-config.module';

@Component({
  selector: 'app-game-modal',
  standalone: true,
  imports: [TranslateModule, TranslateConfigModule],
  templateUrl: './game-modal.component.html',
})
export class GameModalComponent {
  @Input() idGame!: number;
  @Output() gameModalEvent = new EventEmitter<string>();

  constructor(
    protected translate: TranslateService,
    protected gameService: GameService,
    protected toast: ToastrService
  ) {}

  /**
   * Eliminar registro
   */
  protected deleteGame(): void {
    this.gameService
      .deleteGameById(this.idGame)
      .pipe(take(1))
      .subscribe({
        next: (res) => {
          this.gameModalEvent.emit('gameDeleted');
        },
        error: (e) => {
          this.translate.get('error').subscribe((msg: string) => {
            this.toast.error(msg);
          });
        }
      });
  }
}
